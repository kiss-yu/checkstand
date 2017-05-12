/**
 * Created by 11723 on 2017/5/11.
 */
var wsHost = "ws://localhost:80";
var webSocket = null;
if ("WebSocket" in window) {
    webSocket = new WebSocket(wsHost + "/WebSocket/message");
    webSocket.onerror = function(event) {
        // console.info(event);
    };
    webSocket.onopen = function(event) {
        // console.info(event);

    };
    webSocket.onmessage = function(event) {
        messageList.push(new CreateDiv(event.data));
    };
    webSocket.onclose = function(event) {
        // console.info(event);
    };
    window.onbeforeunload = function(event) {
        webSocket.close();
    };
}
function send(type) {
    webSocket.send(type);
}
window.onresize=function(){
    setBackImage();
};