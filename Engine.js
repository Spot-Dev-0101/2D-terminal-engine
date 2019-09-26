var Display = require("./Display")
var Object = require("./Object")
'use strict';
const ioHook = require('iohook');

ioHook.on("keydown", event => {
    var dir = getMoveDirFromKey(event)
    player.move(dir[0], dir[1])
    display.Update([ground, player])
});
//Register and stark hook 
ioHook.start();


var display = new Display(80, 20, "▓")
var ground = new Object([[0, 18], [80, 18], [80, 20], [0, 20]])
var player = new Object([[2, 14], [5, 14], [5, 17], [2, 17]])
//console.log(object.maxX, object.minX, object.maxY, object.minY)
display.Update([ground, player])
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