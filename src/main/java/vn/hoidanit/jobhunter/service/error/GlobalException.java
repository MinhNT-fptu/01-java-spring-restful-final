package vn.hoidanit.jobhunter.service.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = IdInvalidException.class) // Annotation lắng nghe => trường hợp nào trả kết quả của nó
    public ResponseEntity<String> handleIdException(IdInvalidException idException) {
        return ResponseEntity.badRequest().body(idException.getMessage());
    }
}
