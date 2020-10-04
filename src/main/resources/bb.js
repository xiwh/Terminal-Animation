var v =$("#collapsibleTable0 td[title]");
var arr = [];
for(var i=0;i<v.length;i++){
    var item = $(v[i]);
    var bg = item.attr("title");
    if(bg){
        bg = bg.replace("#","0x");
        bg = hex2int(bg);
        arr.push(bg);
    }
}
function hex2int(hex) {
    var len = hex.length, a = new Array(len), code;
    for (var i = 0; i < len; i++) {
        code = hex.charCodeAt(i);
        if (48<=code && code < 58) {
            code -= 48;
        } else {
            code = (code & 0xdf) - 65 + 10;
        }
        a[i] = code;
    }

    return a.reduce(function(acc, c) {
        acc = 16 * acc + c;
        return acc;
    }, 0);
}
console.log(JSON.stringify(arr));