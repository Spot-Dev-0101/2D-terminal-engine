var Display = require("./Display")
var Object = require("./Object")
var Vector2 = require("./Vector2")
'use strict';
//const ioHook = require('iohook');

//ioHook.on("keydown", event => {
//    var dir = getMoveDirFromKey(event)
//    tri.move(dir[0], dir[1])
//    display.Update([tri])
//});
//Register and stark hook 
//ioHook.start();

var display = new Display(80, 20, "▓")
//var shape = new Object([[]])
var tri = new Object([new Vector2(1, 1), new Vector2(6, 4), new Vector2(17, 0), new Vector2(20, 10), new Vector2(1, 10)])
//var tri = new Object([[1, 1], [6, 4], [17, 0], [20, 10], [1, 10]])//new Object([[10, 3], [25, 8], [10, 8]])
//console.log(object.maxX, object.minX, object.maxY, object.minY)
display.Update([tri])



  
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