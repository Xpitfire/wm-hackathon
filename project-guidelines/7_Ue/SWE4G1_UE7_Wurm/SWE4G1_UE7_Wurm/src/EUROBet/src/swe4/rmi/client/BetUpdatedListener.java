package swe4.rmi.client;

import swe4.models.Bet;

public interface BetUpdatedListener {
	public void onBetUpdated(Bet bet);

}
