package fund.investment.userinterface.distribution;

import java.time.LocalDateTime;
import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fund.investment.domain.queryhandler.distribution.command.DistributionAllEventQuerier;
import fund.investment.domain.queryhandler.distribution.command.DistributionEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.distribution.DistributionEventEntry;
import fund.investment.util.SwaggerTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/investment/distributions")
public class DistributionController {
	
	@Autowired
    private QueryGateway queryGateway;
	
	@RequestMapping(value = "/{distId}", method = RequestMethod.GET)
	@ApiOperation(value = "findById", tags = SwaggerTag.DISTRIBUTION)
	public ResponseEntity<DistributionEventEntry> findById(@PathVariable("distId") String distId) {
		try {
			DistributionEventEntry result = queryGateway.query(DistributionEventByIdQuerier.builder().id(distId).build(), ResponseTypes.instanceOf(DistributionEventEntry.class)).join();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "findAll", tags = SwaggerTag.DISTRIBUTION)
	public ResponseEntity<List<DistributionEventEntry>> findAll() {
		try {
			List<DistributionEventEntry> result = queryGateway.subscriptionQuery(DistributionAllEventQuerier.builder().time(LocalDateTime.now()).build(), ResponseTypes.multipleInstancesOf(DistributionEventEntry.class), ResponseTypes.instanceOf(DistributionEventEntry.class)).initialResult().block();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
