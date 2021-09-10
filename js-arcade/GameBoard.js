function GameBoard(containerNode, width, height, handleClick, handleRightClick) {
    var buttons = [];
    for (var i = 0; i < width; i++)
        buttons.push([]);
    containerNode.innerHTML = "";
    var table = document.createElement("table");
    for (var y = 0; y < height; y++) {
        var row = document.createElement("tr");
        for (var x = 0; x < width; x++) {
            var cell = document.createElement("td");
            cell.className = "board_cell";
            var button = document.createElement("button");
            button.onclick = (function(x, y) {
                return function() {
                    handleClick(x, y);
                };
            })(x, y);
            if (handleRightClick !== undefined) {
                button.oncontextmenu = (function(x, y) {
                    return function() {
                        handleRightClick(x, y);
                        return false;
                    };
                })(x, y);
            }
            buttons[x][y] = button;
            cell.appendChild(button);
            row.appendChild(cell);
        }
        table.appendChild(row);
    }
    containerNode.appendChild(table);
    this.getButton = function(x, y) {
        return buttons[x][y];
    }
}
