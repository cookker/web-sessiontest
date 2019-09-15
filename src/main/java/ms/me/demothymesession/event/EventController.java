package ms.me.demothymesession.event;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/events")
@SessionAttributes(types = Event.class)
public class EventController {

    @GetMapping("/form/name")
    public String eventsFormName(Model model) {
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }

    @PostMapping("/form/name")
    public String eventsFormNameSubmmit(@Validated(Event.FirstValidGroup.class) @ModelAttribute Event event,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/events/form-name";
        }

        return "redirect:/events/form/limit";
    }

    @GetMapping("/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event,
                                  Model model) {
        model.addAttribute("event", event);
        return "/events/form-limit";
    }

    @PostMapping("/form/limit")
    public String eventsFormLimitSubmit(@Validated(Event.SecondValidGroup.class) @ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/events/form-limit";
        }
        sessionStatus.setComplete();

//        attributes.addAttribute("name", event.getName());
//        attributes.addAttribute("limit", event.getLimit());
        attributes.addFlashAttribute("newEvent",event); //httpSession을 통해 데이터를 전달한다.

        return "redirect:/events/list";
    }

    @GetMapping("/list")
    public String getEvents(Model model,
                            @SessionAttribute("firstTime") LocalDateTime firstConnectionTime,
                            //@Event객체를 세션에서 먼저 찾기 때문에 세션에 정의한 이름과 다른 이름으로 선언해야 한다.
                            @ModelAttribute("newEvent") Event newEvent) {

        System.out.println(firstConnectionTime);
        model.addAttribute("eventList", List.of(newEvent));
        return "/events/list";
    }
}
