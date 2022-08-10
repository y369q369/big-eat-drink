// pages/order/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        orderId: '',
        orderList: [],
        totalPrice: 0,
        orderStatus: 1
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        const eventChannel = this.getOpenerEventChannel()
        var that = this
        // 监听 order 事件，获取上一页面通过 eventChannel 传送到当前页面的数据
        eventChannel.on('order', function (data) {
            that.setData({
                orderId: data.id
            })
        })
        this.initOrder()
    },

    /**
     * 初始化订单
     */
    initOrder: function (e) {
        var that = this
        wx.request({
            url: 'http://localhost:8281/customer/order/orderInfo?orderId=' + that.data.orderId,
            method: 'GET', 
            success: (res) => {
                that.setData({
                    orderList: res.data.data.orderList,
                    totalPrice: res.data.data.totalPrice,
                    orderStatus: res.data.data.status,
                })
            },
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

    back: function() {
        wx.navigateBack({})
    },

    /**
     * 结账
     */
    updateOrder: function() {
        if (this.data.orderStatus == 1) {
            var that = this
            wx.request({
                url: 'http://localhost:8281/customer/order/order',
                method: 'PUT',
                data: {
                    id: that.data.orderId,
                    status: 2
                },
                success: (res) => {
                    that.setData({
                        orderStatus: 2,
                    })
                },
                fail: function (res) {
                    console.error(res)
                    wx.showToast({
                        icon: "none",
                        mask: true,
                        title: "接口调用失败，请稍后再试。",
                    });
                }
            })
        }
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