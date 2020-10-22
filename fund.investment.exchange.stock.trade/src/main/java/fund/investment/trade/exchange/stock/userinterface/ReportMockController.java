package fund.investment.trade.exchange.stock.userinterface;

import fund.investment.trade.exchange.stock.domain.eventhandler.ESReportHandler;
import fund.investment.trade.exchange.stock.mock.report.*;
import infrastructure.trade.domain.model.command.CancelConfirmOrderCmd;
import infrastructure.trade.domain.model.command.FillOrderCmd;
import infrastructure.trade.domain.model.command.PlaceCancelOrderCmd;
import infrastructure.trade.domain.model.command.PlaceConfirmOrderCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/report/mock/zmp")
public class ReportMockController {

    private final ESReportHandler reportHandler;

    @Autowired
    public ReportMockController(ESReportHandler reportHandler) {
        this.reportHandler = reportHandler;
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    @ApiOperation(value = "placeResponse", tags = SwaggerTag.REPORT_MOCK)
    public ResponseEntity<Void> placeResponse(@RequestBody ZmpPlaceResponse body) {
        try {
            reportHandler.receivePlaceResponse(body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/placed", method = RequestMethod.POST)
    @ApiOperation(value = "placedReport", tags = SwaggerTag.REPORT_MOCK)
    public ResponseEntity<PlaceConfirmOrderCmd> placedReport(@RequestBody ZmqPlacedReport body) {
        try {
            reportHandler.receivedPlacedReport(body);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cancellation", method = RequestMethod.POST)
    @ApiOperation(value = "cancell", tags = SwaggerTag.REPORT_MOCK)
    public ResponseEntity<PlaceCancelOrderCmd> cancell(@RequestBody ZmqCancelResponse body) {
        try {
            reportHandler.receiveCancelResponse(body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cancelled-confirm", method = RequestMethod.POST)
    @ApiOperation(value = "cancelledConfirm", tags = SwaggerTag.REPORT_MOCK)
    public ResponseEntity<CancelConfirmOrderCmd> cancelledConfirm(@RequestBody ZmqCancellationReport body) {
        try {
            reportHandler.ReceiveCancellationReport(body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fill", method = RequestMethod.POST)
    @ApiOperation(value = "filled", tags = SwaggerTag.REPORT_MOCK)
    public ResponseEntity<FillOrderCmd> fill(@RequestBody ZmqFillReport body) {
        try {
            reportHandler.receiveFillReport(body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
