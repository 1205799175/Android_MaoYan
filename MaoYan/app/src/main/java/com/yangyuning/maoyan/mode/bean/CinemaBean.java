package com.yangyuning.maoyan.mode.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/20.
 * 影院实体类
 * @author 姜鑫
 */
public class CinemaBean {


    /**
     * code : 0
     * data : {"cinemas":[{"addr":"瓦房店市家乐福华太财富广场4楼","follow":0,"id":13250,"lat":39.647865,"lng":122.003586,"mark":0,"nm":"中影明生国际影城(华太财富广场店)","poiId":42859762,"promotion":{"platformActivityTag":"惊天破特惠"},"referencePrice":"0","sellPrice":"26","tag":{"allowRefund":0,"buyout":0,"deal":1,"endorse":0,"sell":1,"snack":1,"vipTag":"折扣卡"}},{"addr":"西岗区五四路66号恒隆广场4层","follow":0,"id":15649,"lat":38.90762,"lng":121.60824,"mark":0,"nm":"百丽宫影城(恒隆广场店)","poiId":71219253,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"0","sellPrice":"19.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"sell":1,"snack":0}},{"addr":"金州区经济技术开发区辽河西路117号万达广场四楼","follow":0,"id":13597,"lat":39.060417,"lng":121.78464,"mark":0,"nm":"万达国际影城(经开万达广场店)","poiId":67446563,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"0","sellPrice":"19.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["IMAX厅","4D厅"],"sell":1,"snack":1}},{"addr":"中山区天津街国泰港汇中心6层（新世界百货西侧）","follow":0,"id":12380,"lat":38.92116,"lng":121.63822,"mark":0,"nm":"万达国际影城(港汇店)","poiId":40378054,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"0","sellPrice":"14.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["4DX厅"],"sell":1,"snack":1}},{"addr":"甘井子区高新园区黄浦路500号万达广场4层","follow":0,"id":6151,"lat":38.861305,"lng":121.53311,"mark":0,"nm":"万达国际影城(高新店)","poiId":2496123,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"50","sellPrice":"19.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["IMAX厅","4DX厅"],"sell":1,"snack":1}},{"addr":"中山区鲁迅路12-1一方国际大厦四楼","follow":0,"id":8094,"lat":38.921535,"lng":121.64778,"mark":0,"nm":"万达国际影城(一方国际广场店)","poiId":3313742,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"0","sellPrice":"14.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["杜比全景声厅"],"sell":1,"snack":1}},{"addr":"西岗区中山路281号悦荟广场3层","follow":0,"id":1501,"lat":38.91072,"lng":121.60831,"mark":0,"nm":"万达国际影城(华府店)","poiId":1560445,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"60","sellPrice":"19.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["IMAX厅"],"sell":1,"snack":1}},{"addr":"甘井子区西南路122-130号新玛特新华店4楼东南侧","follow":0,"id":1652,"lat":38.945396,"lng":121.58811,"mark":0,"nm":"大地数字影院(新华绿洲店)","poiId":1166264,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"30","sellPrice":"18","tag":{"allowRefund":1,"buyout":0,"deal":0,"endorse":1,"sell":1,"snack":1,"vipTag":"折扣卡"}},{"addr":"金州区斯大林路677号安盛商业广场6楼","follow":0,"id":1496,"lat":39.102695,"lng":121.716545,"mark":0,"nm":"华臣影城(金州店)","poiId":1550134,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"50","sellPrice":"14.9","tag":{"allowRefund":1,"buyout":0,"deal":1,"endorse":1,"sell":1,"snack":1}},{"addr":"中山区同兴街31-8号（悦泰街里小区内）","follow":0,"id":2226,"lat":38.92407,"lng":121.64437,"mark":0,"nm":"金逸影城(悦泰店)","poiId":1543596,"promotion":{"platformActivityTag":"惊天破特惠"},"referencePrice":"60","sellPrice":"18.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"sell":1,"snack":1}}],"ct_pois":[{"ct_poi":"936879945111165696_a13250_c0","poiid":42859762},{"ct_poi":"936879945111165696_a15649_c1","poiid":71219253},{"ct_poi":"936879945111165696_a13597_c2","poiid":67446563},{"ct_poi":"936879945111165696_a12380_c3","poiid":40378054},{"ct_poi":"936879945111165696_a6151_c4","poiid":2496123},{"ct_poi":"936879945111165696_a8094_c5","poiid":3313742},{"ct_poi":"936879945111165696_a1501_c6","poiid":1560445},{"ct_poi":"936879945111165696_a1652_c7","poiid":1166264},{"ct_poi":"936879945111165696_a1496_c8","poiid":1550134},{"ct_poi":"936879945111165696_a2226_c9","poiid":1543596}],"paging":{"hasMore":true,"limit":10,"offset":0,"total":49}}
     * success : true
     */

    private int code;
    /**
     * cinemas : [{"addr":"瓦房店市家乐福华太财富广场4楼","follow":0,"id":13250,"lat":39.647865,"lng":122.003586,"mark":0,"nm":"中影明生国际影城(华太财富广场店)","poiId":42859762,"promotion":{"platformActivityTag":"惊天破特惠"},"referencePrice":"0","sellPrice":"26","tag":{"allowRefund":0,"buyout":0,"deal":1,"endorse":0,"sell":1,"snack":1,"vipTag":"折扣卡"}},{"addr":"西岗区五四路66号恒隆广场4层","follow":0,"id":15649,"lat":38.90762,"lng":121.60824,"mark":0,"nm":"百丽宫影城(恒隆广场店)","poiId":71219253,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"0","sellPrice":"19.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"sell":1,"snack":0}},{"addr":"金州区经济技术开发区辽河西路117号万达广场四楼","follow":0,"id":13597,"lat":39.060417,"lng":121.78464,"mark":0,"nm":"万达国际影城(经开万达广场店)","poiId":67446563,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"0","sellPrice":"19.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["IMAX厅","4D厅"],"sell":1,"snack":1}},{"addr":"中山区天津街国泰港汇中心6层（新世界百货西侧）","follow":0,"id":12380,"lat":38.92116,"lng":121.63822,"mark":0,"nm":"万达国际影城(港汇店)","poiId":40378054,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"0","sellPrice":"14.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["4DX厅"],"sell":1,"snack":1}},{"addr":"甘井子区高新园区黄浦路500号万达广场4层","follow":0,"id":6151,"lat":38.861305,"lng":121.53311,"mark":0,"nm":"万达国际影城(高新店)","poiId":2496123,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"50","sellPrice":"19.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["IMAX厅","4DX厅"],"sell":1,"snack":1}},{"addr":"中山区鲁迅路12-1一方国际大厦四楼","follow":0,"id":8094,"lat":38.921535,"lng":121.64778,"mark":0,"nm":"万达国际影城(一方国际广场店)","poiId":3313742,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"0","sellPrice":"14.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["杜比全景声厅"],"sell":1,"snack":1}},{"addr":"西岗区中山路281号悦荟广场3层","follow":0,"id":1501,"lat":38.91072,"lng":121.60831,"mark":0,"nm":"万达国际影城(华府店)","poiId":1560445,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"60","sellPrice":"19.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"hallType":["IMAX厅"],"sell":1,"snack":1}},{"addr":"甘井子区西南路122-130号新玛特新华店4楼东南侧","follow":0,"id":1652,"lat":38.945396,"lng":121.58811,"mark":0,"nm":"大地数字影院(新华绿洲店)","poiId":1166264,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"30","sellPrice":"18","tag":{"allowRefund":1,"buyout":0,"deal":0,"endorse":1,"sell":1,"snack":1,"vipTag":"折扣卡"}},{"addr":"金州区斯大林路677号安盛商业广场6楼","follow":0,"id":1496,"lat":39.102695,"lng":121.716545,"mark":0,"nm":"华臣影城(金州店)","poiId":1550134,"promotion":{"platformActivityTag":"惊天破等2部影片特惠"},"referencePrice":"50","sellPrice":"14.9","tag":{"allowRefund":1,"buyout":0,"deal":1,"endorse":1,"sell":1,"snack":1}},{"addr":"中山区同兴街31-8号（悦泰街里小区内）","follow":0,"id":2226,"lat":38.92407,"lng":121.64437,"mark":0,"nm":"金逸影城(悦泰店)","poiId":1543596,"promotion":{"platformActivityTag":"惊天破特惠"},"referencePrice":"60","sellPrice":"18.9","tag":{"allowRefund":0,"buyout":0,"deal":0,"endorse":0,"sell":1,"snack":1}}]
     * ct_pois : [{"ct_poi":"936879945111165696_a13250_c0","poiid":42859762},{"ct_poi":"936879945111165696_a15649_c1","poiid":71219253},{"ct_poi":"936879945111165696_a13597_c2","poiid":67446563},{"ct_poi":"936879945111165696_a12380_c3","poiid":40378054},{"ct_poi":"936879945111165696_a6151_c4","poiid":2496123},{"ct_poi":"936879945111165696_a8094_c5","poiid":3313742},{"ct_poi":"936879945111165696_a1501_c6","poiid":1560445},{"ct_poi":"936879945111165696_a1652_c7","poiid":1166264},{"ct_poi":"936879945111165696_a1496_c8","poiid":1550134},{"ct_poi":"936879945111165696_a2226_c9","poiid":1543596}]
     * paging : {"hasMore":true,"limit":10,"offset":0,"total":49}
     */

    private DataBean data;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * hasMore : true
         * limit : 10
         * offset : 0
         * total : 49
         */

        private PagingBean paging;
        /**
         * addr : 瓦房店市家乐福华太财富广场4楼
         * follow : 0
         * id : 13250
         * lat : 39.647865
         * lng : 122.003586
         * mark : 0
         * nm : 中影明生国际影城(华太财富广场店)
         * poiId : 42859762
         * promotion : {"platformActivityTag":"惊天破特惠"}
         * referencePrice : 0
         * sellPrice : 26
         * tag : {"allowRefund":0,"buyout":0,"deal":1,"endorse":0,"sell":1,"snack":1,"vipTag":"折扣卡"}
         */

        private List<CinemasBean> cinemas;
        /**
         * ct_poi : 936879945111165696_a13250_c0
         * poiid : 42859762
         */

        private List<CtPoisBean> ct_pois;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<CinemasBean> getCinemas() {
            return cinemas;
        }

        public void setCinemas(List<CinemasBean> cinemas) {
            this.cinemas = cinemas;
        }

        public List<CtPoisBean> getCt_pois() {
            return ct_pois;
        }

        public void setCt_pois(List<CtPoisBean> ct_pois) {
            this.ct_pois = ct_pois;
        }

        public static class PagingBean {
            private boolean hasMore;
            private int limit;
            private int offset;
            private int total;

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class CinemasBean {
            private String addr;
            private int follow;
            private int id;
            private double lat;
            private double lng;
            private int mark;
            private String nm;
            private int poiId;
            /**
             * platformActivityTag : 惊天破特惠
             */

            private PromotionBean promotion;
            private String referencePrice;
            private String sellPrice;
            /**
             * allowRefund : 0
             * buyout : 0
             * deal : 1
             * endorse : 0
             * sell : 1
             * snack : 1
             * vipTag : 折扣卡
             */

            private TagBean tag;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public int getFollow() {
                return follow;
            }

            public void setFollow(int follow) {
                this.follow = follow;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public int getMark() {
                return mark;
            }

            public void setMark(int mark) {
                this.mark = mark;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public int getPoiId() {
                return poiId;
            }

            public void setPoiId(int poiId) {
                this.poiId = poiId;
            }

            public PromotionBean getPromotion() {
                return promotion;
            }

            public void setPromotion(PromotionBean promotion) {
                this.promotion = promotion;
            }

            public String getReferencePrice() {
                return referencePrice;
            }

            public void setReferencePrice(String referencePrice) {
                this.referencePrice = referencePrice;
            }

            public String getSellPrice() {
                return sellPrice;
            }

            public void setSellPrice(String sellPrice) {
                this.sellPrice = sellPrice;
            }

            public TagBean getTag() {
                return tag;
            }

            public void setTag(TagBean tag) {
                this.tag = tag;
            }

            public static class PromotionBean {
                private String platformActivityTag;

                public String getPlatformActivityTag() {
                    return platformActivityTag;
                }

                public void setPlatformActivityTag(String platformActivityTag) {
                    this.platformActivityTag = platformActivityTag;
                }
            }

            public static class TagBean {
                private int allowRefund;
                private int buyout;
                private int deal;
                private int endorse;
                private int sell;
                private int snack;
                private String vipTag;

                public int getAllowRefund() {
                    return allowRefund;
                }

                public void setAllowRefund(int allowRefund) {
                    this.allowRefund = allowRefund;
                }

                public int getBuyout() {
                    return buyout;
                }

                public void setBuyout(int buyout) {
                    this.buyout = buyout;
                }

                public int getDeal() {
                    return deal;
                }

                public void setDeal(int deal) {
                    this.deal = deal;
                }

                public int getEndorse() {
                    return endorse;
                }

                public void setEndorse(int endorse) {
                    this.endorse = endorse;
                }

                public int getSell() {
                    return sell;
                }

                public void setSell(int sell) {
                    this.sell = sell;
                }

                public int getSnack() {
                    return snack;
                }

                public void setSnack(int snack) {
                    this.snack = snack;
                }

                public String getVipTag() {
                    return vipTag;
                }

                public void setVipTag(String vipTag) {
                    this.vipTag = vipTag;
                }
            }
        }

        public static class CtPoisBean {
            private String ct_poi;
            private int poiid;

            public String getCt_poi() {
                return ct_poi;
            }

            public void setCt_poi(String ct_poi) {
                this.ct_poi = ct_poi;
            }

            public int getPoiid() {
                return poiid;
            }

            public void setPoiid(int poiid) {
                this.poiid = poiid;
            }
        }
    }
}
