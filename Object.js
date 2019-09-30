class Object{
    constructor(points){
        this.points = points //2d array. each sub array is the X, Y position
        this.maxX = this.maxPos(0)
        this.minX = this.minPos(0)
        this.maxY = this.maxPos(1)
        this.minY = this.minPos(1)
        this.midX = this.midPoint(this.minX, this.maxX)
        this.midY = this.midPoint(this.minY, this.maxY)
    }

    maxPos(index){
        var array = this.points.map(x => x[index])
        return Math.max(...array)
    }
    minPos(index){
        var array = this.points.map(x => x[index])
        return Math.min(...array)
    }

    midPoint(min, max){
        return Math.round((min+max)/2)
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
        this.midX = this.midPoint(this.minX, this.maxX)
        this.midY = this.midPoint(this.minY, this.maxY)
    }
}

module.exports = Object