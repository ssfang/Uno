import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Uno {

	/**
	 * UNO每副游戏牌共有108张卡，游戏牌分四种颜色：红色、绿色、蓝色及黄色，每种颜色各有25张牌，其中19张为数字牌
	 * （0牌有一张，1-9有两张），其余6张为功能牌
	 * ："skip"（跳牌）、"draw two"（罚牌2张）及"reverse"（反转出牌方向）。另有黑色特别牌8张
	 * ："wild"（转色）及"wild draw four"（转色及罚牌4张），每种各4张。
	 * 
	 * <p>
	 * The aim of the game is to be the first player to score 500 points. This
	 * is achieved (usually over several rounds of play) by a player discarding
	 * all of their cards and earning points corresponding to the value of the
	 * remaining cards still held by the other players.
	 * </p>
	 * <p>
	 * The deck consists of 108 cards, of which there are twenty-five of each
	 * color (red, green, blue, and yellow), each color having two of each rank
	 * except zero. The ranks in each color are zero to nine, "Skip", "Draw Two"
	 * and "Reverse" (the last three of these being classified as
	 * "action cards"). In addition, the deck contains four each of "Wild" and
	 * "Wild Draw Four" cards.
	 * </p>
	 * <p>
	 * To start a hand, seven cards are dealt out to each player, and the top
	 * card of the deck is flipped over and set aside to begin the discard pile.
	 * The player to the dealer's left plays first, unless the first card on the
	 * discard pile is an action or Wild card (see below). On a player's turn,
	 * he/she must do one of the following:
	 * </p>
	 * <ul>
	 * <li>play a card matching the discard in color, number or symbol</li>
	 * <li>play a Wild card, or a playable Wild Draw Four card (see restriction
	 * below)</li>
	 * <li>draw the top card of the deck</li>
	 * </ul>
	 * <p>
	 * If a player chooses to draw the top card of the deck, and that card is
	 * playable (it matches the discard, or is a playable wild card), then the
	 * player may (but need not) immediately play that card.
	 * </p>
	 * <p>
	 * Play proceeds clockwise around the table.
	 * </p>
	 * <p>
	 * Action and Wild cards have the following effects:
	 * </p>
	 * <table class="wikitable plainrowheaders">
	 * <tbody>
	 * <tr>
	 * <th>Card</th>
	 * <th>Effect when played from hand</th>
	 * <th>Effect as first discard</th>
	 * </tr>
	 * <tr>
	 * <td>Skip</td>
	 * <td>Next player in sequence loses a turn</td>
	 * <td>Player to dealer's left loses a turn</td>
	 * </tr>
	 * <tr>
	 * <td>Draw Two</td>
	 * <td>Next player in sequence draws two cards and loses a turn</td>
	 * <td>Player to dealer's left draws two cards and loses a turn.</td>
	 * </tr>
	 * <tr>
	 * <td>Reverse</td>
	 * <td>Order of play switches directions (clockwise to counterclockwise, and
	 * vice versa)</td>
	 * <td>Dealer plays first; play proceeds counterclockwise</td>
	 * </tr>
	 * <tr>
	 * <td>Wild</td>
	 * <td>Player declares next color to be matched (may be used on any turn
	 * even if the player has matching color)</td>
	 * <td>Player to dealer's left declares first color to be matched, then
	 * plays normally</td>
	 * </tr>
	 * <tr>
	 * <td>Wild Draw Four</td>
	 * <td>Player declares next color to be matched; next player in sequence
	 * draws four cards and loses a turn. May be legally played only if the
	 * player has no cards of the current color; Wild cards and cards with the
	 * same number or symbol in a different color do not count.</td>
	 * <td>Return card to deck, shuffle, flip top card to start discard pile</td>
	 * </tr>
	 * </tbody>
	 * </table>
	 * <ul>
	 * <li>A player may draw a card from the deck even if that player has a
	 * playable card.</li>
	 * <li>If a player chooses to draw a card and the drawn card is playable,
	 * the player has the option of either keeping it or playing it immediately
	 * (as part of that turn).</li>
	 * <li>If a player does not have a playable card, the player will continue
	 * to draw until a playable card is drawn. If deck is empty during draw,
	 * flip over used cards and continue.</li>
	 * <li>A player may play a Wild card at any time (even if that player has
	 * other playable cards).</li>
	 * <li>A player may play a Wild Draw Four card only if that player has no
	 * cards matching the current <i>color</i> (the player may have cards of a
	 * different color matching the current number or symbol or a Wild
	 * card).<sup id="cite_ref-3" class="reference"><a
	 * href="#cite_note-3"><span>[</span>3<span>]</span></a></sup> A player who
	 * plays a Wild Draw Four may be challenged by the next player in sequence
	 * (see below).</li>
	 * <li>If the entire deck is used during play, the top discard is set aside
	 * and the rest of the pile is shuffled to create a new deck. Play then
	 * proceeds normally.</li>
	 * <li>It is illegal to trade cards of any sort with another player.</li>
	 * <li>In a two-player game, the Reverse has the same effect as a Skip,
	 * allowing the player who discards it to take another turn.</li>
	 * </ul>
	 * <p>
	 * <br>
	 * A player who plays his/her next-to-last card must call "uno" as a warning
	 * to the others.
	 * 
	 * @author fangss
	 * 
	 */
	static class UnoCard {
		UnoCardColor color;
		int rank;

		private UnoCard(UnoCardColor color, UnoCardRank rank) {
			super();
			this.color = color;
			this.rank = rank.ordinal();
		}

		private UnoCard(UnoCardColor color, int rank) {
			super();
			this.color = color;
			this.rank = rank;
		}

		public UnoCardColor getColor() {
			return color;
		}

		public int getRank() {
			return rank;
		}

		public void setColor(UnoCardColor color, int rank) {
			this.color = color;
			this.rank = rank;
		}

		@Override
		public String toString() {
			return "UnoCard_" + color + "_" + rank;
		}

		/**
		 * The ranks in each color are zero to nine, "Skip", "Draw Two" and
		 * "Reverse" (the last three of these being classified as
		 * "action cards"). In addition, the deck contains four each of "Wild"
		 * and "Wild Draw Four" cards.
		 * 
		 */
		public enum UnoCardRank {
			ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEEN, EIGTH, NINE, SKIP, DRAW_TWO, REVERSE, WILD, WILD_DRAW_FOUR
		}

		public enum UnoCardColor {
			BLACK, RED, GREEN, BLUE, YELLOW;
			static UnoCardColor[] colors = new UnoCardColor[] { UnoCardColor.RED, UnoCardColor.GREEN,
					UnoCardColor.BLUE, UnoCardColor.YELLOW };
		}

		/** 生成一副牌 */
		public static UnoCard[] generateDeck() {
			UnoCard[] unoCards = new UnoCard[108];
			int idx = 0;
			for (UnoCardColor color : UnoCardColor.values()) {
				if (UnoCardColor.BLACK == color) {
					UnoCard blackWildCard = new UnoCard(UnoCardColor.BLACK, UnoCardRank.WILD);
					UnoCard blackWildDrawFourCard = new UnoCard(UnoCardColor.BLACK, UnoCardRank.WILD_DRAW_FOUR);
					Arrays.fill(unoCards, idx, idx += 4, blackWildCard);
					Arrays.fill(unoCards, idx, idx += 4, blackWildDrawFourCard);
				} else {
					unoCards[idx++] = new UnoCard(color, 0);
					for (int rank = 1; rank < 1 + 9 + 3; rank++) {
						UnoCard colorCard = new UnoCard(color, rank);
						unoCards[idx++] = colorCard;
						unoCards[idx++] = colorCard;
					}
				}

			}
			return unoCards;
		}

		/**
		 * Check whether the card {@code one} is playable(it matches the
		 * discard, or is a playable wild card)<br>
		 * 玩者发出的牌，须与上个玩者发出的牌之颜色或数字相同，但黑色特别牌则无此限制，可任意发出。
		 * 
		 * @param one a
		 * @param against The card discarded by the previous player on the
		 *            discard pile.
		 * @return
		 */
		static boolean isPlayable(UnoCard one, UnoCard against) {
			if (one.rank == against.rank || one.color == against.color || one.color == UnoCardColor.BLACK) {
				return true;
			}
			return false;
		}

	}

	static class UnoPlayer {
		public ArrayList<UnoCard> cards = new ArrayList<UnoCard>();

		@Override
		public String toString() {
			return "UnoPlayer [cards=" + cards + "]";
		}
	}

	public static void mainTest(String[] args) {
		UnoPlayer[] unoPlayers = new UnoPlayer[4];

		UnoCard[] unoCards = UnoCard.generateDeck();
		List<UnoCard> unoCardList = Arrays.asList(unoCards);

		Collections.shuffle(unoCardList);
		System.out.println(Arrays.toString(unoCards));

		for (int playIndex = 0; playIndex < 4; playIndex++) {
			UnoPlayer unoPlayer = new UnoPlayer();
			unoPlayers[playIndex] = unoPlayer;
			int startIndex = playIndex * 7;
			unoPlayer.cards.addAll(unoCardList.subList(startIndex, startIndex + 7));

			System.out.println(playIndex + ": " + unoPlayer.cards);
		}

	}
}
