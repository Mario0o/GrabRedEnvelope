package com.carlos.grabredenvelope.data

import com.carlos.cutils.base.CBasePreferences
import com.carlos.cutils.util.LogUtils
import com.carlos.grabredenvelope.MyApplication
import com.carlos.grabredenvelope.dao.DeviceInformationVO
import com.carlos.grabredenvelope.dao.WechatControlVO
import kotlinx.serialization.json.JSON

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
*/

/**
 * Created by Carlos on 2019/2/21.
 */
object RedEnvelopePreferences :
    CBasePreferences("redenvelope_preferences", MyApplication.instance.applicationContext) {

    private val IMEI = "imei"
    private val DEVICE_INFORMATION = "device_information"
    private val WECHAT_CONTROL = "wechat_control"

    var imei: String
        get() = getString(IMEI, "")
        set(value) = setString(IMEI, value)

    var deviceInformaiton: DeviceInformationVO
        get() {
            val data = getString(DEVICE_INFORMATION, "")
            if (data.isNullOrEmpty()) return DeviceInformationVO()
            return try {
                JSON.parse(DeviceInformationVO.serializer(), data)
            } catch (e: Exception) {
                LogUtils.e("error:", e)
                setString(
                    DEVICE_INFORMATION,
                    JSON.stringify(DeviceInformationVO.serializer(), DeviceInformationVO())
                )
                DeviceInformationVO()
            }
        }
        set(value) {
            setString(DEVICE_INFORMATION, JSON.stringify(DeviceInformationVO.serializer(), value))
        }


    var wechatControl: WechatControlVO
        get() {
            val data = getString(WECHAT_CONTROL, "")
            if (data.isNullOrEmpty()) return WechatControlVO()
            return try {
                JSON.parse(WechatControlVO.serializer(), data)
            } catch (e: Exception) {
                LogUtils.e("error:", e)
                setString(
                    WECHAT_CONTROL,
                    JSON.stringify(WechatControlVO.serializer(), WechatControlVO())
                )
                WechatControlVO()
            }
        }
        set(value) {
            setString(WECHAT_CONTROL, JSON.stringify(WechatControlVO.serializer(), value))
        }


}


