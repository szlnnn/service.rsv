package mohi.wedding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wedding/rsvp")
class FormController {

    @Autowired
    private FormService formService;

    @PostMapping
    public ResponseEntity<String> receiveForm(@RequestBody FormData formData) {
        formService.processForm(formData);
        return ResponseEntity.ok("Form received");
    }
}