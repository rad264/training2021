const compShips = {
	"carrier": {
		size: 5,
		hp: 5,
		sunk: false,
	},
	"battleship": {
		size: 4,
		hp: 4,
		sunk: false,
	},
	"cruiser": {
		size: 3,
		hp: 3,
		sunk: false,
	},
	"submarine": {
		size: 3,
		hp: 3,
		sunk: false,
	},
	"destroyer": {
		size: 2,
		hp: 2,
		sunk: false,
	},
}

const playerShips = {
	"carrier": {
		size: 5,
		hp: 5,
		sunk: false,
	},
	"battleship": {
		size: 4,
		hp: 4,
		sunk: false,
	},
	"cruiser": {
		size: 3,
		hp: 3,
		sunk: false,
	},
	"submarine": {
		size: 3,
		hp: 3,
		sunk: false,
	},
	"destroyer": {
		size: 2,
		hp: 2,
		sunk: false,
	},
}

function BattleShip() {
    var thisObj = this;
	this.gameOver = false;
    this.playerIsNext = true;
	this.gameStarted = false;
    this.player = new Player(thisObj)
	this.comp = new Comp(thisObj)
	let notifications = document.getElementById('messages')
	notifications.innerHTML = '<div id="title">Notifications</div>'
	let directions = document.getElementById("direction-text")
	directions.innerHTML = "Place ships on <strong>Player's Grid</strong><br>Next ship: <strong>CARRIER (5)</strong>"
	let compTitle = document.getElementById("comp-title")
	let playerTitle = document.getElementById("player-title")
	compTitle.innerHTML = "Opponent's Grid"
	playerTitle.innerHTML = "Players's Grid"
	this.playerShips = {
		"carrier": {
			size: 5,
			hp: 5,
			sunk: false,
		},
		"battleship": {
			size: 4,
			hp: 4,
			sunk: false,
		},
		"cruiser": {
			size: 3,
			hp: 3,
			sunk: false,
		},
		"submarine": {
			size: 3,
			hp: 3,
			sunk: false,
		},
		"destroyer": {
			size: 2,
			hp: 2,
			sunk: false,
		}
	}
	this.compShips = {
		"carrier": {
			size: 5,
			hp: 5,
			sunk: false,
		},
		"battleship": {
			size: 4,
			hp: 4,
			sunk: false,
		},
		"cruiser": {
			size: 3,
			hp: 3,
			sunk: false,
		},
		"submarine": {
			size: 3,
			hp: 3,
			sunk: false,
		},
		"destroyer": {
			size: 2,
			hp: 2,
			sunk: false,
		},
	}
	
}      