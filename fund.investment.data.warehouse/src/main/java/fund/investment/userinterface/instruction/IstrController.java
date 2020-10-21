package fund.investment.userinterface.instruction;

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
import fund.investment.domain.queryhandler.instruction.command.IstrAllEventQuerier;
import fund.investment.domain.queryhandler.instruction.command.IstrEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.instruction.IstrEventEntry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/investment/instructions")
public class IstrController {
	
	@Autowired
    private QueryGateway queryGateway;
	
	@RequestMapping(value = "/{istrId}", method = RequestMethod.GET)
	@ApiOperation(value = "findById", tags = SwaggerTag.INSTRUCTION)
	public ResponseEntity<IstrEventEntry> findById(@PathVariable("istrId") String istrId) {
		try {
			IstrEventEntry result = queryGateway.query(IstrEventByIdQuerier.builder().id(istrId).build(), ResponseTypes.instanceOf(IstrEventEntry.class)).join();
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "findAll", tags = SwaggerTag.INSTRUCTION)
	public ResponseEntity<List<IstrEventEntry>> findAll() {
		try {
			List<IstrEventEntry> result = queryGateway.subscriptionQuery(
					IstrAllEventQuerier.builder().build(), 
					ResponseTypes.multipleInstancesOf(IstrEventEntry.class),
					ResponseTypes.instanceOf(IstrEventEntry.class)).initialResult().block();
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
