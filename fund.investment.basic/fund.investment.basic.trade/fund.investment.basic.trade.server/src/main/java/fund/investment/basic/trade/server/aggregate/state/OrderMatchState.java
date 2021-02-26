package fund.investment.basic.trade.server.aggregate.state;


import fund.investment.basic.trade.api.command.*;

public interface OrderMatchState {

    default void match(MatchOrderCmd cmd) {
    }

    default void cancelMatch(CancelMatchOrderCmd cmd) {
    }

    default void autoMatch(AutoMatchOrderCmd cmd) {
    }

    default void autoCancel(AutoCancelMatchOrderCmd cmd) {
    }

    default void matchRollback(PROrderMatchRollbackCmd cmd){ }

    default void unmatchRollback(PROrderCancelMatchRollbackCmd cmd){ }

    default void matching(PRMatchOrderCmd cmd){ }

    default void unmatching(PRCancelMatchOrderCmd cmd){ }

}
