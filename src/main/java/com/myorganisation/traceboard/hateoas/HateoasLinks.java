package com.myorganisation.traceboard.hateoas;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HateoasLinks {
    private List<Link> links = new ArrayList<>();

    public void addLink(Link link) {
        links.add(link);
    }
}
