// pages/login/index.js
var app = getApp()

Page({

    /**
     * 页面的初始数据
     */
    data: {
        avatarUrl: 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0',
        nickName: ''
    },

    onChooseAvatar(e) {
        const { avatarUrl } = e.detail
        this.setData({
            avatarUrl,
        })
    },

    changeNickName(e) {
        this.setData({
            nickName: e.detail.value
        })
    },

    submit() {
        if (this.data.nickName === '') {
            wx.showToast({
                title: '请输入昵称',
                icon: "none",
                duration: 1500,
                mask: true
            })
        } else {
            app.globalData.user.avatarUrl = this.data.avatarUrl
            app.globalData.user.nickName = this.data.nickName

            wx.request({
                url: 'http://localhost:8281/customer/appUser/user',
                method: 'POST',
                data: app.globalData.user,
                success: (res) => {
                    if (res.data.data > 0) {
                        wx.setStorage({
                            key: "user",
                            data: app.globalData.user,
                            encrypt: true
                        })
                        wx.switchTab({
                            url: '/pages/index/index',
                        })
                    }
                    
                },
                // 请求失败时的一些处理
                fail: function (res) {
                    console.error(res)
                    wx.showToast({
                        icon: "none",
                        mask: true,
                        title: "微信授权失败",
                    });
                }
            })
        }
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})