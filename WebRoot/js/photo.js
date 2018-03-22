/**
 * 轮播图,  
 * 通过setImg 来跟换图片,  Imgindex 表示图片序号(数组下标),  timeCount 计时
 */

var timeCount = 0;//计时, 非countTime()调用setImg() 要清零,  否则会连续更换图片
var imgIndex = 1;//图片地址数组下标.
var imgs = new Array();
var time = null;





function setImg() {
	//取s数组第index个元素赋给img
	
	var img = document.getElementById("img");
	img.src = imgs[imgIndex++];
	imgIndex = imgIndex % imgs.length;
	
	//console.log("5s");

};

//计时更换图片,
function countTime(){//每隔t秒,更换一次图片(调用setImg(),
	timeCount+=1;
	//console.log("1s");
	if(timeCount >= 5){
		timeCount = 0;//归零,
		setImg();
	}
	time=setTimeout("countTime()",1000)//递归调用
};