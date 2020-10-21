package fund.investment.userinterface.approval;

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
import fund.investment.domain.queryhandler.approval.command.AprvAllEventQuerier;
import fund.investment.domain.queryhandler.approval.command.AprvEventByIdQuerier;
import fund.investment.infrastructure.repository.db.dao.approval.AprvEventEntry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/dataset/approvals")
public class AprvController {
	
	@Autowired
    private QueryGateway queryGateway;
	
	@RequestMapping(value = "/{aprvId}", method = RequestMethod.GET)
	@ApiOperation(value = "findById", tags = SwaggerTag.INSTRUCTION_APPROVAL)
	public ResponseEntity<AprvEventEntry> findById(@PathVariable("aprvId") String id) {
		try {
			AprvEventEntry result = queryGateway.query(AprvEventByIdQuerier.builder().id(id).build(), ResponseTypes.instanceOf(AprvEventEntry.class)).join();
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "findAll", tags = SwaggerTag.INSTRUCTION_APPROVAL)
	public ResponseEntity<List<AprvEventEntry>> findAll() {
		try {
			List<AprvEventEntry> result = queryGateway.subscriptionQuery(AprvAllEventQuerier.builder().time(LocalDateTime.now()).build(), ResponseTypes.multipleInstancesOf(AprvEventEntry.class), ResponseTypes.instanceOf(AprvEventEntry.class)).initialResult().block();
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
