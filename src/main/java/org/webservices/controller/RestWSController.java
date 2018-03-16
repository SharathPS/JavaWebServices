package org.webservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webservices.service.RestOptions;


@RestController
@RequestMapping("/restws")
public class RestWSController {

	@Autowired
	private RestOptions restOptions;

	@RequestMapping(value = "/push", method = RequestMethod.POST)
	public String  push(@RequestParam(value="i1",required=true ) int i1, @RequestParam(value="i2",required=true ) int i2) {
		 return restOptions.push(i1, i2);
	}


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Integer> list() {
		return restOptions.list();
	}


}
