class Object{
    constructor(points){
        this.points = points //2d array. each sub array is the X, Y position
        this.maxX = this.maxPos(0)
        this.minX = this.minPos(0)
        this.maxY = this.maxPos(1)
        this.minY = this.minPos(1)
    }

    maxPos(index){
        var array = this.points.map(x => x[index])
        return Math.max(...array)
    }
    minPos(index){
        var array = this.points.map(x => x[index])
        return Math.min(...array)
    }

    move(x, y){
        this.points.forEach(point => {
            point[0] += x
            point[1] += y
        });
        this.maxX = this.maxPos(0)
        this.minX = this.minPos(0)
        this.maxY = this.maxPos(1)
        this.minY = this.minPos(1)
    }
}

module.exports = Object