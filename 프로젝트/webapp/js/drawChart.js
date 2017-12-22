function drawChart() {
	var Traffic=0;
	var Lodge=0;
	var Admission=0;
	var Food=0;
	var Shopping=0;
	var Besides=0;
	var tbd = document.getElementById('tbd');
	var thd = document.getElementById('thd');
	var thd_th = thd.getElementsByTagName('th');
	var tbd_tr = tbd.getElementsByTagName('tr');
	for(var j=0;j<thd_th.length-1;j++){
		for(var i=0;i< tbd_tr.length; i++){
			var string='schedule-'+j+'-'+i;
			schedule=document.getElementById(string);
			if($(schedule).has('#listdiv').length){
				var listdiv=$(schedule).find('#listdiv');
				var moneytraffic=$(listdiv).find('#moneydiv').find('#moneytraffic').val();
				var moneylodge=$(listdiv).find('#moneydiv').find('#moneylodge').val();
				var moneyadmission=$(listdiv).find('#moneydiv').find('#moneyadmission').val();
				var moneyfood=$(listdiv).find('#moneydiv').find('#moneyfood').val();
				var moneyshopping=$(listdiv).find('#moneydiv').find('#moneyshopping').val();
				var moneybesides=$(listdiv).find('#moneydiv').find('#moneybesides').val();

				Traffic+=Number(moneytraffic);
				Lodge+=Number(moneylodge);
				Admission+=Number(moneyadmission);
				Food+=Number(moneyfood);
				Shopping+=Number(moneyshopping);
				Besides+=Number(moneybesides);
			}
		}
	}
    var data = google.visualization.arrayToDataTable([
        ["Element", "Money", { role: "style" } ],
        ["Traffic", Traffic, "#b87333"],
        ["Lodge", Lodge, "silver"],
        ["Admixxsion", Admission, "gold"],
        ["Food", Food, "color: #e5e4e2"],
        ["Shopping", Shopping, "color: #e5e4e2"],
        ["Besides", Besides, "color: #b87333"]
    ]);

    var view = new google.visualization.DataView(data);
    view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);
    var options = {
      width: 600,
      height: 400,
      bar: {groupWidth: "50%"},
      legend: {
      	position: "none"
      },
    };
    var chart = new google.visualization.BarChart(document.getElementById("moneybook"));
    chart.draw(view, options);
}