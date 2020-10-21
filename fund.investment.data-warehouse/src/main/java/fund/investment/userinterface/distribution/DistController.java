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

import fund.investment.infrastructure.util.SwaggerTag;
import fund.investment.domain.queryhandler.distribution.command.DistAllEventQuerier;
import fund.investment.domain.queryhandler.distribution.command.DistEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.distribution.DistEventEntry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/investment/distributions")
public class DistController {
	
	@Autowired
    private QueryGateway queryGateway;
	
	@RequestMapping(value = "/{distId}", method = RequestMethod.GET)
	@ApiOperation(value = "findById", tags = SwaggerTag.INSTRUCTION_DISTRIBUTION)
	public ResponseEntity<DistEventEntry> findById(@PathVariable("distId") String distId) {
		try {
			DistEventEntry result = queryGateway.query(DistEventByIdQuerier.builder().id(distId).build(), ResponseTypes.instanceOf(DistEventEntry.class)).join();
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "findAll", tags = SwaggerTag.INSTRUCTION_DISTRIBUTION)
	public ResponseEntity<List<DistEventEntry>> findAll() {
		try {
			List<DistEventEntry> result = queryGateway.subscriptionQuery(DistAllEventQuerier.builder().time(LocalDateTime.now()).build(), ResponseTypes.multipleInstancesOf(DistEventEntry.class), ResponseTypes.instanceOf(DistEventEntry.class)).initialResult().block();
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
