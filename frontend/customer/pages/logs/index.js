// pages/logs/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {

    },

    test() {
        // 为确保this指向不发生改变，可以固定下this指向
        //使用this的时候用that代替即可
        var that = this
        wx.request({
            // 注意，如果小程序开启校验合法域名时必须使用https协议
            //在测试的情况下可以不开启域名校验
            url: 'http://localhost:8082/test',
            // 请求的方法
            method: 'GET', // 或 ‘POST’
            // 设置请求头，不能设置 Referer
            // 请求成功时的处理
            success: function (res) {
                // 一般在这一打印下看看是否拿到数据
                console.log(res)
                console.log(res.data)
            },
            // 请求失败时的一些处理
            fail: function (res) {
                console.error(res)
                wx.showToast({
                    icon: "none",
                    mask: true,
                    title: "接口调用失败，请稍后再试。",
                });
            }
        })
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