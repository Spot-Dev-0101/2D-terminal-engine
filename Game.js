var Display = require("./Display")
var Object = require("./Object")
'use strict';
const ioHook = require('iohook');

ioHook.on("keydown", event => {
    var dir = getMoveDirFromKey(event)
    tri.move(dir[0], dir[1])
    display.Update([tri])
});
//Register and stark hook 
ioHook.start();


var display = new Display(80, 20, "▓")
//var shape = new Object([[]])
var tri = new Object([[1, 1], [6, 4], [17, 0], [20, 10], [1, 10]])//new Object([[10, 3], [25, 8], [10, 8]])
//console.log(object.maxX, object.minX, object.maxY, object.minY)
display.Update([tri])
  //console.log(object.points)
  //display.Update(object)

var fps = 30

var frames = 0
var frame;




  
function getMoveDirFromKey(event){
  if(event.keycode == "32"){//d
    return [1, 0]
  } else if(event.keycode == "30"){//a
    return [-1, 0]
  } else if(event.keycode == "17"){//w
    return [0, -1]
  } else if(event.keycode == "31"){//s
    return [0, 1]
  } 
  return [0, 0]
}