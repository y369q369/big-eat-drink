// pages/index/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        curNav: 1
    },

    /* 把点击到的某一项 设为当前curNav */
    switchRightTab: function (e) {
        let id = e.target.dataset.id;
        console.log(id);
        this.setData({
            curNav: id
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        // this.setData({
        //     nbTitle: '新标题',
        //     nbLoading: true,
        //     nbFrontColor: '#ffffff',
        //     nbBackgroundColor: '#000000',
        //   })
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