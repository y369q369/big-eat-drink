// pages/logs/index.js
Page({
    /**
     * 页面的初始数据
     */
    data: {
        curNav: 1,
        catalogList: [],
        menuMap: {},
        menuDetail: {},
        choose: {},
        height: {
            catalog: {}
        },
        showCatalog: '',
        number: {
            catalog: {},
            total: 0
        },
        showModalStatus: false,
        orderId: '',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
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
                if (res.data.catalogList.length > 0 && Object.keys(res.data.menuMap).length > 0) {
                    var catalogHeight = {}
                    var tempHeight = 0
                    var tempNumber = {}
                    var tempMenuDetail = {}

                    res.data.catalogList.forEach(catalog => {
                        tempHeight += res.data.menuMap[catalog.id].length * 52 + 40
                        catalogHeight[tempHeight] = 'C' + catalog.id
                        tempNumber[catalog.id] = 0
                        res.data.menuMap[catalog.id].forEach(menu => {
                            tempMenuDetail[menu.id] = menu;
                        })
                    })

                    that.setData({
                        curNav: res.data.catalogList[0].id,
                        catalogList: res.data.catalogList,
                        menuMap: res.data.menuMap,
                        menuDetail: tempMenuDetail,
                        ['height.catalog']: catalogHeight,
                        'number.catalog': tempNumber
                    })
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
    switchRightTab: function (e) {
        var catalog = e.target.dataset.item
        this.setData({
            curNav: catalog.id,
            showCatalog: 'C' + catalog.id
        })
    },

    /**
     * 滚动事件
     */
    scroll: function (e) {
        var keys = Object.keys(this.data.height.catalog)
        var tempCatalog = this.data.height.catalog[keys[0]]
        for (var i = 0; i < keys.length; i++) {
            if (e.detail.scrollTop >= keys[i]) {
                if (i < keys.length - 1) {
                    tempCatalog = this.data.height.catalog[keys[i + 1]];
                }
            } else {
                if (tempCatalog != this.data.showCatalog) {
                    this.setData({
                        curNav: tempCatalog.substring(1),
                        showCatalog: tempCatalog
                    })
                }
                break
            }
        }
    },

    /**
     * 减少数量
     */
    minNumber: function (e) {
        var menu = e.target.dataset.item
        var currentNum = this.data.choose[menu.id];
        if (currentNum) {
            if (currentNum == 1) {
                this.setData({
                    ['choose.' + menu.id]: 0
                })
            } else {
                this.setData({
                    ['choose.' + menu.id]: --currentNum
                })
            }
        }

        var tempNumber = this.data.number
        tempNumber.catalog[menu.catalogId] -= 1
        tempNumber.total -= 1
        this.setData({
            number: tempNumber
        })
    },

    /**
     * 增加数量
     */
    plusNumber: function (e) {
        var menu = e.target.dataset.item
        var currentNum = this.data.choose[menu.id];
        var key = "choose." + menu.id
        if (currentNum) {
            this.setData({
                [key]: ++currentNum
            })
        } else {
            this.setData({
                [key]: 1
                // ['choose.' + menu.id]: 1
            })
        }

        var tempNumber = this.data.number
        tempNumber.catalog[menu.catalogId] += 1
        tempNumber.total += 1
        this.setData({
            number: tempNumber
        })
    },

    /** 
     * 显示对话框
     */
    showModal: function () {
        // 显示遮罩层
        var animation = wx.createAnimation({
            duration: 200,
            timingFunction: "linear",
            delay: 0
        })
        this.animation = animation
        animation.translateY(500).step()
        this.setData({
            animationData: animation.export(),
            showModalStatus: true
        })
        setTimeout(function () {
            animation.translateY(0).step()
            this.setData({
                animationData: animation.export()
            })
        }.bind(this), 200)
    },

    /** 
     * 隐藏对话框
     */
    hideModal: function () {
        // 隐藏遮罩层
        var animation = wx.createAnimation({
            duration: 200,
            timingFunction: "linear",
            delay: 0
        })
        this.animation = animation
        animation.translateY(300).step()
        this.setData({
            animationData: animation.export(),
        })
        setTimeout(function () {
            animation.translateY(0).step()
            this.setData({
                animationData: animation.export(),
                showModalStatus: false
            })
        }.bind(this), 200)
    },



    /**
     * 新增订单
     */
    saveOrder: function (e) {
        var that = this
        wx.request({
            url: 'http://localhost:8281/customer/order/order',
            method: 'POST',
            data: {
                orderId: this.data.orderId,
                userId: 'gs',
                detailMap: this.data.choose
            },
            success: (res) => {
                if (res.data.code == 200) {
                    var tempOrderId = res.data.data.id
                    that.setData({
                        curNav: 1,
                        choose: {},
                        showCatalog: '',
                        number: {
                            catalog: {},
                            total: 0
                        },
                        showModalStatus: false,
                        orderId: tempOrderId
                    })
                    wx.navigateTo({
                        url: '../order/index',
                        success: function (res) {
                            // 通过 eventChannel 向被打开页面传送数据
                            res.eventChannel.emit('order', {
                                id: tempOrderId
                            })
                        }
                    })
                } else {
                    wx.showToast({
                        icon: "none",
                        mask: true,
                        title: "接口调用失败，请稍后再试。",
                    });
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