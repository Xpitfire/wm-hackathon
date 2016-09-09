package swe4.rmi.client;

import swe4.models.Bet;

public interface BetAddedListener {
	public void onBetAdded(Bet bet);

}
