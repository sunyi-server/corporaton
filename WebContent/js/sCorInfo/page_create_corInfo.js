window.onload = initialization;

function initialization() {
	
		getCorInfoGmtCreate();
	
}

function getCorInfoGmtCreate() {
	var dtCur = new Date();
	var yearCur = dtCur.getFullYear();
	var monCur = dtCur.getMonth() + 1;
	var dayCur = dtCur.getDate();
	var hCur = dtCur.getHours();
	var mCur = dtCur.getMinutes();
	var sCur = dtCur.getSeconds();
	var timeCur = yearCur + "-" + (monCur < 10 ? "0" + monCur : monCur) + "-"
			+ (dayCur < 10 ? "0" + dayCur : dayCur) + " "
			+ (hCur < 10 ? "0" + hCur : hCur) + ":"
			+ (mCur < 10 ? "0" + mCur : mCur) + ":"
			+ (sCur < 10 ? "0" + sCur : sCur);


	var input_corporation_apply_time = document.getElementById("input_corporation_apply_time");
	input_corporation_apply_time.value = timeCur;
}