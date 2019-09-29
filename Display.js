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
        //console.clear()
        for(var y = 0; y < this.height; y++){
            var row = []
            for(var x = 0; x < this.width; x++){
                row.push(this.emptyChar)
            }
            this.pixels.push(row)
        }
        //console.log("sdfsdf: ", this.pixels, this.pixels[1].length)
        //object.points.forEach(point => {
            //this.pixels[point[1]-1][point[0]-1] = this.char
        //});
        objects.forEach(object => {
            var outLinePixels = this.pixelsOnOutline(object.points)
            outLinePixels.forEach(pixel => {
                this.pixels[pixel[0]][pixel[1]] = "\x1b[36m"+this.char+"\x1b[0m"
            })
            this.pixelsInBounds(outLinePixels)
            //this.pixelsInBounds(object.maxX, object.minX, object.maxY, object.minY).forEach(pixel => {
                //this.pixels[pixel[0]][pixel[1]] = this.char
            //});
        });
        
        var output = ""
        this.pixels.forEach(row => {
            var rowStr = ""
            row.forEach(pixel => {
                rowStr += pixel
            });
            //console.log(rowStr)
            output += rowStr + "\n"
        });
        process.stdout.clearLine();
        process.stdout.cursorTo(0);
        process.stdout.write(output);
    }

    pixelsOnOutline(points){
        var pixels = []
        for(var i = 0; i < points.length; i++){
            
            var start = points[i]
            var end = points[i+1]

            if(i == points.length-1){
                end = points[0]
            }

            if(end[0] < start[0]){
                start = points[i+1]
                end = points[i]
            }
            var length = Math.sqrt(((end[0]*end[0])-(start[0]*start[0]))+((end[1]*end[1])-(start[1]*start[1])))
            for(var z = 0; z < length; z++){
                var t = z/length
                var y = Math.round((1-t)*start[0]+t*end[0])
                var x = Math.round((1-t)*start[1]+t*end[1])
                pixels.push([x, y])
            }
        }
        return pixels
    }

    pixelsInBounds(outline){
        var pixels = [] 

        console.log(outline)

        return pixels
    }

}
module.exports = Display
