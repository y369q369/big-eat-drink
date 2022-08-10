App({
    /**
     * 全局变量
     */
    globalData: {
        system: {},
        user: {},
    },

    onLaunch: function () {
        // 隐藏底部导航栏
        wx.hideTabBar()
        this.getSystemInfo()

    },

    /**
     * 获取系统配置信息
     */
    getSystemInfo: function () {
        wx.request({
            url: 'http://localhost:8281/customer/system/systemConfig',
            method: 'GET',
            success: (info) => {
                var data = info.data.data
                this.globalData.system = data
                this.initUser()

                wx.setStorage({
                    key: "system",
                    data: data,
                    encrypt: true
                })
            }
        })
    },

    /**
     * 初始化用户信息
     */
    initUser: function () {
        var that = this
        wx.getStorage({
            key: "user",
            encrypt: true,
            success(user) {
                that.globalData.user = user.data
            },
            fail(e) {
                wx.login({
                    success(res) {
                        if (res.code) {
                            wx.request({
                                url: 'https://api.weixin.qq.com/sns/jscode2session?appid=' + that.globalData.system.AppID + '&secret=' + that.globalData.system.AppSecret + '&js_code=' + res.code + '&grant_type=authorization_code',
                                method: 'GET',
                                success: (user) => {
                                    wx.request({
                                        url: 'http://localhost:8281/customer/appUser/user?openid=' + user.data.openid,
                                        method: 'GET',
                                        success: (data) => {
                                            if (data.data.data.nickName) {
                                                that.globalData.user = data.data.data
                                            } else {
                                                that.globalData.user.openid = user.data.openid
                                                that.globalData.user.sessionKey = user.data.session_key
                                            }

                                            wx.setStorage({
                                                key: "user",
                                                data: that.globalData.user,
                                                encrypt: true
                                            })
                                        }
                                    })
                                }
                            })
                        }
                    },
                    fail(e) {
                        console.error('网络异常', e)
                    }
                })
            }
        })
    }



})