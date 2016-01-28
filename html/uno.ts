
/**
 * http://www.typescriptlang.org/Playground
 */
class Animal {
    constructor(public name: string) { }
    move(meters: number) {
        alert(this.name + " moved " + meters + "m.");
    }
}

class Snake extends Animal {
    constructor(name: string) { super(name); }
    move() {
        alert("Slithering...");
        super.move(5);
    }
}

class Horse extends Animal {
    constructor(name: string) { super(name); }
    move() {
        alert("Galloping...");
        super.move(45);
    }
}

var sam = new Snake("Sammy the Python");
var tom: Animal = new Horse("Tommy the Palomino");

//sam.move();
//tom.move(34);

/** The red, green, blue, yellow are ordinary cards, the black cards are wild */
enum UnoCardColor{
	RED, GREEN, BLUE, YELLOW, BLACK
}

/**
 * The ranks in each color are zero to nine, "Skip", "Draw Two" and
 * "Reverse" (the last three of these being classified as
 * "action cards"). In addition, the deck contains four each of "Wild"
 * and "Wild Draw Four" cards.
 */
enum UnoCardRank{
	ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEEN, EIGTH, NINE, SKIP, DRAW_TWO, REVERSE, WILD, WILD_DRAW_FOUR
}
/**
 * UNO每副游戏牌共有108张卡，游戏牌分四种颜色：红色、绿色、蓝色及黄色，每种颜色各有25张牌，其中19张为数字牌（0牌
 * 有一张，1-9有两张），其余6张为功能牌："skip"（跳牌）、"draw two"（罚牌2张）及"reverse"（反转出牌方向）。另
 * 有黑色特别牌8张："wild"（转色）及"wild draw four"（转色及罚牌4张），每种各4张。
 */
class UnoCard{
	constructor(private color: UnoCardColor, private rank: number){}
	getColor(){return this.color;}
	getRank(){return this.rank;}
	toString(){
		return UnoCardRank[this.color] + '_' + this.rank;
	}
	/** Rank for the action cards and the black cards: 10 - 12, 13 - 14*/
	static SKIP: number = 10; // the action cards
	static DRAW_TWO: number = 11; // the action cards
	static REVERSE: number = 12;  // the action cards
	static WILD: number = 13; //only for the black cards
	static WILD_DRAW_FOUR: number = 14;  //only for the black cards
	
		/** 生成一副牌 */0
	static generateDeck(): UnoCard[]{
			var unoCards : UnoCard[] = [];
			var idx: number = 0;
			
			function generateOrdinaryCards(color : UnoCardColor){
				unoCards[idx++] = new UnoCard(color, 0);
				for (var rank = 1; rank < 1 + 9 + 3; rank++) {
					var colorCard = new UnoCard(color, rank);
					unoCards[idx++] = colorCard;
					unoCards[idx++] = colorCard;
				}
			}
			//red, green, blue, yellow cards: 0, 1-9 * 2, actions
			generateOrdinaryCards(UnoCardColor.RED);
			generateOrdinaryCards(UnoCardColor.GREEN);
			generateOrdinaryCards(UnoCardColor.BLUE);
			generateOrdinaryCards(UnoCardColor.YELLOW);
			//black cards: two kinds of wild cards * 4
			var blackWildCard = new UnoCard(UnoCardColor.BLACK, UnoCard.WILD);
			var blackWildDrawFourCard = new UnoCard(UnoCardColor.BLACK, UnoCard.WILD_DRAW_FOUR);
			unoCards.push(blackWildCard, blackWildCard, blackWildCard, blackWildCard);
            unoCards.push(blackWildDrawFourCard, blackWildDrawFourCard, blackWildDrawFourCard, blackWildDrawFourCard);
			
			return unoCards;
		}
}

/** 
 * Range: color := [0, 4], rank := [0, 14], 
 * Comibation: 
 * # color_0_rank_13 * 4, color_0_rank_14 * 4
 * # color_1-4_rank_0;
 * # color_1-4_rank_1-12 * 2;
 */
function generateDeck() {
	var cards = [];
	var blackWildCard = 13;
	var blackWildDrawFourCard = 14;
	cards.push(blackWildCard, blackWildCard, blackWildCard, blackWildCard);
	cards.push(blackWildDrawFourCard, blackWildDrawFourCard, blackWildDrawFourCard, blackWildDrawFourCard);
	for(var color = 1; color < 1 + 4; color++){
		cards.push((color << 8));
	for(var rank = 1; rank < 1 + 9 + 3; rank++){
			cards.push((color << 8) + rank);
			cards.push((color << 8) + rank);
		} 
	} 
	return cards;
}
/**
 * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 * http://stackoverflow.com/questions/6274339/how-can-i-shuffle-an-array-in-javascript
 * https://github.com/coolaj86/knuth-shuffle
 */
function shuffle(array) {
	var currentIndex = array.length, temporaryValue, randomIndex;

	// While there remain elements to shuffle...
	while (0 !== currentIndex) {

		// Pick a remaining element...
		randomIndex = Math.floor(Math.random() * currentIndex);
		currentIndex -= 1;

		// And swap it with the current element.
		temporaryValue = array[currentIndex];
		array[currentIndex] = array[randomIndex];
		array[randomIndex] = temporaryValue;
	}

	return array;
}
			
/** UnoPlayer */
class UnoPlayer{
	private cards: UnoCard[];
	constructor(private name: string) { }
	setCards(cards: UnoCard[]){
		this.cards = cards;
	}
	getCards(): UnoCard[]{
		return this.cards;
	}
	discard(card: UnoCard): UnoCard{
		
		return ;
	}
	
	collision(card: UnoCard): UnoCard{
		
		return ;
	}
}


//
var players: UnoPlayer[]= []; //new Array(playerCount);
players.push(new UnoPlayer("me"));
players.push(new UnoPlayer("other_player_1"));
players.push(new UnoPlayer("other_player_2"));
players.push(new UnoPlayer("other_player_3"));

//To start a hand
var cards = UnoCard.generateDeck();

var cardCountEachPlayer = 7;
cards = shuffle(cards);

//DEBUG
console.log(cards);


//Seven cards are dealt out to each player.
var playerCount = players.length;
for(var playerIndex = 0; playerIndex < playerCount; playerIndex++){
	players[playerIndex].setCards(cards.splice(0, cardCountEachPlayer));
}

//DEBUG
console.log(players);

