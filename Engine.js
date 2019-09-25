var Display = require("./Display")
var Object = require("./Object")
'use strict';
const ioHook = require('iohook');

ioHook.on("mousemove", event => {
    object.move(1, 0)
    console.log(object.points)
    display.Update(object)
  // result: {type: 'mousemove',x: 700,y: 400}
});
ioHook.on("keydown", event => {
    object.move(1, 0)
    console.log(object.points)
    display.Update(object)
  // result: {keychar: 'f', keycode: 19, rawcode: 15, type: 'keypress'}
});
//Register and stark hook 
ioHook.start();


var display = new Display(80, 20, "▓")
var object = new Object([[3, 2], [25, 2], [25, 16], [3, 16]])
console.log(object.maxX, object.minX, object.maxY, object.minY)

  //console.log(object.points)
  //display.Update(object)

var fps = 30

var frames = 0
var frame;


(function theLoop (frame) {
    setTimeout(function () {

        object.move(1, 0)
    console.log(object.points)
    display.Update(object)

      if (--frame) {          // If i > 0, keep going
        //theLoop(frame);       // Call the loop again, and pass it the current value of i
      }
    }, (60/fps)*10);
  })(frames);
