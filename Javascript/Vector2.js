class Vector2{
    constructor(x, y){
        this.x = x
        this.y = y
    }

    static convertArray(array){
        return new Vector2(array[0], array[1])
    }
}

module.exports = Vector2