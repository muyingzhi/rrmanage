
var initdata = new Vue({
  el: '.cont',
  data: {
    active:3,
    d1:"",
    d2:"",
    d3:"",
    activeNames: ['1']
  },
  mounted: function () {
    var that = this;
    this.loadData();
  },
  methods: {
    loadData(){
      var that = this;
      axios.post(serverurl + 'api/stat/list').then(function(res) {
        that.d1 = res.data.data.baseinfo;
        that.d3 = res.data.data.followup;
        that.d2 = res.data.data.plan;
      })
    }
   
  }
});