class Display{

    constructor(w, h, char){
        this.height = h
        this.width = w
        this.char = char
        this.emptyChar = "▒"
        this.pixels = [] //2D array. Each sub array is a row
    }

    Update(objects){
        this.pixels = []
        console.clear()
        for(var y = 0; y < this.height; y++){
            var row = []
            for(var x = 0; x < this.width; x++){
                row.push(this.emptyChar)
            }
            this.pixels.push(row)
        }

        //object.points.forEach(point => {
            //this.pixels[point[1]-1][point[0]-1] = this.char
        //});
        objects.forEach(object => {
            this.pixelsInBounds(object.maxX, object.minX, object.maxY, object.minY).forEach(pixels => {
                this.pixels[point[1]-1][point[0]-1] = this.char
            });
        });
        

        this.pixels.forEach(row => {
            var rowStr = ""
            row.forEach(pixel => {
                rowStr += pixel
            });
            console.log(rowStr)
        });

    }

    pixelsInBounds(maxX, minX, maxY, minY){
        var pixels = []

        for(var y = 0; y < this.height; y++){
            for(var x = 0; x < this.width; x++){

                if(x >= minX && x <= maxX && y >= minY && y <= maxY){
                    this.pixels[y][x] = this.char
                }

            }
        }

        return pixels
    }

}
module.exports = Display
