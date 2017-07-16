package com.example.aidlservicedemo.domain;

import com.example.aidlservicedemo.domain.Message;
import com.example.aidlservicedemo.domain.User;

interface IGetMsg{
	List<Message> getMes(in User us);
}