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
                this.pixels[pixel[1]][pixel[0]] = "\x1b[36m"+this.char+"\x1b[0m"
            })
            this.pixelsInBounds(outLinePixels, object)
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
                start = points[0]
                end = points[i]
            }

            if(end[0] < start[0]){
                start = points[i+1]
                end = points[i]
            }
            
            var length = Math.sqrt(((end[0]*end[0])-(start[0]*start[0]))+((end[1]*end[1])-(start[1]*start[1])))
            //console.log(length, start, end, i, ":")
            //console.log(start, end, ((end[0]*end[0])-(start[0]*start[0])), ((end[1]*end[1])-(start[1]*start[1])))
            for(var z = 0; z < Math.abs(length); z++){
                //console.log("It atleast gets here")
                var t = z/length
                var x = Math.round((1-t)*start[0]+t*end[0])
                var y = Math.round((1-t)*start[1]+t*end[1])
                pixels.push([x, y])
                //console.log([x, y], z, t)
            }
        }
        return pixels
    }

    pixelsInBounds(outline, object){
        //outline = outline.sort(function(a,b){
        //    return a[1] - b[1];
        //})
        //var tempRow = []
        //for(var i = 0; i < outline.length-1; i++){
        //    //console.log(outline[i][1], outline[i+1][1])
        //    if(outline[i][1] == outline[i+1][1]){
        //        tempRow.push(outline[i])
        //    } else{
        //        rows.push(tempRow.sort(function(a,b){
        //            return a[0] - b[0];
        //        }))
        //        tempRow = []
        //    }
        //}
        //console.log(rows)

        var start = [object.midX, object.midY]
        this.pixels[start[1]][start[0]] = "\x1b[37n"+this.char+"\x1b[0m"

        var points = [start]
        for(var i = 0; i < points.length; i++){
            var point = points[i]
            
            if(outline.includes(point)){
            } else{
                this.pixels[point[1]][point[0]] = "\x1b[37a"+this.char+"\x1b[0m"
                var n = [point[0], point[1]-1]
                var s = [point[0], point[1]+1]
                var e = [point[0]+1, point[1]]
                var w = [point[0]-1, point[1]]
                //console.log(this.isItemInArray(points, n), this.isItemInArray(points, s), this.isItemInArray(points, e), this.isItemInArray(points, w))
                if(this.isPointValid(n, points) && this.isPointValid(n, outline)){
                    points.push(n)
                    //console.log("N: ", n)
                }
                if(this.isPointValid(s, points) && this.isPointValid(s, outline)){
                    points.push(s)
                    //console.log("S: ", s)
                }
                if(this.isPointValid(e, points) && this.isPointValid(e, outline)){
                    points.push(e)
                    //console.log("E: ", e)
                }
                if(this.isPointValid(w, points) && this.isPointValid(w, outline)){
                    points.push(w)
                    //console.log("W: ", w)
                }
            }
            if(points.length >= 500){
                console.log("Reached limit")
                break
            }
        }

    }

    isPointValid(point, array){
        if(this.isItemInArray(array, point) == false && point[0] >= 0 && point[1] >= 0){
            return true
        }
        return false
    }

    isItemInArray(array, item) {
        for (var i = 0; i < array.length; i++) {
            // This if statement depends on the format of your array
            if (array[i][0] == item[0] && array[i][1] == item[1]) {
                return true;   // Found it
            }
        }
        return false;   // Not found
    }

}
module.exports = Display
