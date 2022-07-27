// pages/logs/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        curNav: 1,
        catalogList: [],
        menuMap: {},
        choose: {},
        number: {
            catalog: {},
            total: 0
        }
    },



    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        // 为确保this指向不发生改变，可以固定下this指向,使用this的时候用that代替即可
        var that = this
        wx.request({
            // 注意，如果小程序开启校验合法域名时必须使用https协议
            //在测试的情况下可以不开启域名校验
            url: 'http://localhost:8281/customer/show/catalogMenuList',
            // 请求的方法
            method: 'GET', // 或 ‘POST’
            // 设置请求头，不能设置 Referer
            // 请求成功时的处理
            success: (res) => {
                // 一般在这一打印下看看是否拿到数据
                // console.log(res)
                if (res.data.catalogList.length > 0 && Object.keys(res.data.menuMap).length > 0 ) {
                    that.setData({
                        curNav: res.data.catalogList[0].id,
                        catalogList: res.data.catalogList,
                        menuMap: res.data.menuMap
                    })
                    console.log(that.data);
                }
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
     * 切换种类
     */
    switchRightTab: function(e) {
        var catalog = e.target.dataset.item
        this.setData({
            curNav: catalog.id
        })
    },

    /**
     * 减少数量
     */
    minNumber: function(e) {
        var menu = e.target.dataset.item
        var currentNum = this.data.choose[menu.id];
        if (currentNum) {
            if (currentNum == 1) {
                var tempChoose = this.data.choose
                delete tempChoose[menu.id]
                this.setData({
                    choose: tempChoose
                })
            } else {
                this.setData({
                    ['choose.' + menu.id]: -- currentNum
                })
            }
        }
        var tempNumber = {
            catalog: {},
            total: 0
        }
        if(Object.keys(this.data.choose).length > 0) {
            Object.keys(this.data.choose).forEach(key => {
                tempNumber.total += this.data.choose[key]
            })
            this.setData({
                number: tempNumber
            })
        }
    },

     /**
     * 增加数量
     */
    plusNumber: function(e) {
        var menu = e.target.dataset.item
        var currentNum = this.data.choose[menu.id];
        var key = "choose." + menu.id
        if (currentNum) {
            this.setData({
                [key]: ++ currentNum
            })
        } else {
            this.setData({
                [key]: 1
                // ['choose.' + menu.id]: 1
            })
        }

        var tempNumber = {
            catalog: {},
            total: 0
        }
        if(Object.keys(this.data.choose).length > 0) {
            Object.keys(this.data.choose).forEach(key => {
                tempNumber.total += this.data.choose[key]
            })
            this.setData({
                number: tempNumber
            })
        }
    },

    showShop: function(e) {
        console.log("show shop")
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