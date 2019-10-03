var Vector2 = require("./Vector2")
class Object{
    constructor(points){
        this.points = points //an array of Vector2s
        this.midPoint = this.midPoint(this.points)
        console.log(this.midPoint)
    }

    midPoint(points){
        var midX = 0
        var midY = 0
        points.forEach(point => {
            midX += point.x
            midY += point.y
        });
        return new Vector2(midX/points.length, midY/points.length)
    }

    move(x, y){
        this.points.forEach(point => {
            point[0] += x
            point[1] += y
        });
        this.midPoint = this.midPoint(this.points)
    }
}

module.exports = Object