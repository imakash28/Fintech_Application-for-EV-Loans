package com.kilpi.finayo.Service.Impl;

import java.util.Optional;

import com.kilpi.finayo.Domain.DSAEntity;
import com.kilpi.finayo.Repository.DsaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.kilpi.finayo.Domain.BankEntity;
import com.kilpi.finayo.Domain.ExecutiveEntity;
import com.kilpi.finayo.Domain.UserEntity;
import com.kilpi.finayo.Repository.BankRepository;
import com.kilpi.finayo.Repository.ExecutiveRepository;
import com.kilpi.finayo.Repository.UserRepository;
import com.kilpi.finayo.Service.UserService;
import com.kilpi.finayo.exception.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ExecutiveRepository executiveRepository;

	@Autowired
	DsaRepository dsaRepository;

	@Autowired
    BankRepository bankRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public ExecutiveEntity getExecutive() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User map = (User) auth.getPrincipal();
		UserEntity user = userRepository.findByUsername(map.getUsername());
		Optional<ExecutiveEntity> executive = executiveRepository.findByCode(user.getUniqueId());
		if (!executive.isPresent()) {
			throw new NotFoundException("Unable to find executive details on above code " + user.getUniqueId());
		}
		return executive.get();
	}
	
	@Override
	public UserEntity getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User map = (User) auth.getPrincipal();
		return userRepository.findByUsername(map.getUsername());
	}

	@Override
	public BankEntity getBankDetails() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User map = (User) auth.getPrincipal();
		UserEntity user = userRepository.findByUsername(map.getUsername());
		Optional<BankEntity> bank = bankRepository.findByCode(user.getUniqueId());
		if (!bank.isPresent()) {
			throw new NotFoundException("Unable to find Bank details on above code " + user.getUniqueId());
		}
		return bank.get();
	}

	@Override
	public DSAEntity getDsaDetails() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User map = (User) auth.getPrincipal();
		UserEntity user = userRepository.findByUsername(map.getUsername());
		Optional<DSAEntity> dsa = dsaRepository.findByCode(user.getUniqueId());
		if (!dsa.isPresent()) {
			throw new NotFoundException("Unable to find Dealer details on above code " + user.getUniqueId());
		}
		return dsa.get();
	}

	@Override
	public String getRoleFromAuth() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User map = (User) auth.getPrincipal();
		UserEntity user = userRepository.findByUsername(map.getUsername());
		return user.getRole();
	}

	

}
