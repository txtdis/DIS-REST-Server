package ph.txtdis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Channel;
import ph.txtdis.repository.ChannelRepository;

@RestController("channelController")
@RequestMapping("/channels")
public class ChannelController extends NameListController<ChannelRepository, Channel> {
}