/**
 * Date and time
 */
function dateToString(dateNTime){
	var dateString = '';
	var newDate = iso8610ToJSDate(dateNTime);
	dateString += (''+numberToString(newDate.getHours(),2)+':'+numberToString(newDate.getMinutes(),2)+':'+numberToString(newDate.getSeconds(),2)+' ngày '+numberToString(newDate.getDay(),2)+'-'+numberToString(newDate.getMonth(),2)+'-'+numberToString(newDate.getYear()+1900,4));
	return dateString;
}

function iso8610ToJSDate(dateStr){
	dateStr = dateStr.replace(/\D/g," ");
	var dateComps = dateStr.split(" ");
	dateComps[1]--;
	var convertedDate = new Date(dateComps[0],dateComps[1],dateComps[2],dateComps[3],dateComps[4],dateComps[5]);
	return convertedDate;
}

function numberToString(number,len){
	var strNum = ''+number;
	for (var i = 0; i<(len-strNum.length); i++){
		strNum = '0'+strNum;
	}
	return strNum;
}