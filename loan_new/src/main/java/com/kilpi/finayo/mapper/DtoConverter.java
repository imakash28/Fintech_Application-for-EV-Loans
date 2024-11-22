package com.kilpi.finayo.mapper;

import com.kilpi.finayo.Constant.LoanStatus;

import com.kilpi.finayo.Domain.Loan.*;
import com.kilpi.finayo.Domain.LoanEntity;
import com.kilpi.finayo.VO.Loan.*;
import org.springframework.stereotype.Service;

import com.kilpi.finayo.VO.LoanVO;

@Service
public class DtoConverter {
	


	public ProfileEntity profileVotoEntity(ProfileVO profileVO) {
		ProfileEntity profile = new ProfileEntity();
		profile.setName(profileVO.getName());
		profile.setFsName(profileVO.getFsName());
		profile.setDob(profileVO.getDob());
		profile.setGender(profileVO.getGender());
		profile.setMobile(profileVO.getMobile());
		profile.setAadhar(profileVO.getAadhar());
		profile.setIsAadharVerified(profileVO.getIsAadharVerified());
		profile.setPan(profileVO.getPan());
		profile.setIsPanVerified(profileVO.getIsPanVerified());
		profile.setDrivingLicence(profileVO.getDrivingLicence());
		profile.setIsDrivingLicenceVerified(profileVO.getIsDrivingLicenceVerified());
		profile.setElectricity(profileVO.getElectricity());
		profile.setIsElectricityVerified(profileVO.getIsElectricityVerified());
		profile.setCibil(profileVO.getCibil());
		return profile;
	}
	public COProfileEntity profileVotoEntity(COProfileVO profileVO) {
		COProfileEntity profile = new COProfileEntity();
		profile.setName(profileVO.getName());
		profile.setFsName(profileVO.getFsName());
		profile.setDob(profileVO.getDob());
		profile.setGender(profileVO.getGender());
		profile.setMobile(profileVO.getMobile());
		profile.setAadhar(profileVO.getAadhar());
		profile.setIsAadharVerified(profileVO.getIsAadharVerified());
		profile.setPan(profileVO.getPan());
		profile.setIsPanVerified(profileVO.getIsPanVerified());
		profile.setDrivingLicence(profileVO.getDrivingLicence());
		profile.setIsDrivingLicenceVerified(profileVO.getIsDrivingLicenceVerified());
		profile.setElectricity(profileVO.getElectricity());
		profile.setIsElectricityVerified(profileVO.getIsElectricityVerified());
		profile.setCibil(profileVO.getCibil());
		return profile;
	}

	public AddressEntity addressVotoEntity(AddressVO addressVO) {
		AddressEntity address = new AddressEntity();
		address.setCrAddress(addressVO.getCrAddress());
		address.setPrAddress(addressVO.getPrAddress());
		address.setDuration(addressVO.getDuration());
		address.setResidenceType(addressVO.getResidenceType());
		address.setApplicantRelation(addressVO.getApplicantRelation());
		return address;
	}

	public COAddressEntity coAddressVotoEntity(COAddressVO addressVO) {
		COAddressEntity address = new COAddressEntity();
		address.setCrAddress(addressVO.getCrAddress());
		address.setPrAddress(addressVO.getPrAddress());
		address.setDuration(addressVO.getDuration());
		address.setResidenceType(addressVO.getResidenceType());
		address.setApplicantRelation(addressVO.getApplicantRelation());
		return address;
	}

	public IncomeEntity incomeVotoEntity(IncomeVO incomeVO) {
		IncomeEntity income = new IncomeEntity();
		income.setOccupationType(incomeVO.getOccupationType());
		income.setCurrentOccupation(incomeVO.getCurrentOccupation());
		income.setMonthlyEarnings(incomeVO.getMonthlyEarnings());
		income.setExpIncomeEarnings(incomeVO.getExpIncomeEarnings());
		income.setExtraSourceIncome(incomeVO.getExtraSourceIncome());
		income.setVehicleType(incomeVO.getVehicleType());
		income.setDrivingExperience(incomeVO.getDrivingExperience());
		income.setSourceTotalIncome(incomeVO.getSourceTotalIncome());
		income.setSourceIncomeRemarks(incomeVO.getSourceIncomeRemarks());
		return income;
	}

	public COIncomeEntity coIncomeVotoEntity(COIncomeVO incomeVO) {
		COIncomeEntity income = new COIncomeEntity();
		income.setOccupationType(incomeVO.getOccupationType());
		income.setCurrentOccupation(incomeVO.getCurrentOccupation());
		income.setMonthlyEarnings(incomeVO.getMonthlyEarnings());
		income.setExpIncomeEarnings(incomeVO.getExpIncomeEarnings());
		income.setExtraSourceIncome(incomeVO.getExtraSourceIncome());
		income.setVehicleType(incomeVO.getVehicleType());
		income.setDrivingExperience(incomeVO.getDrivingExperience());
		income.setSourceTotalIncome(incomeVO.getSourceTotalIncome());
		income.setSourceIncomeRemarks(incomeVO.getSourceIncomeRemarks());
		return income;
	}

	public AssetsEntity assetVotoEntity(AssetsVO assetVO) {
		AssetsEntity asset = new AssetsEntity();
		asset.setAssetType(assetVO.getVehicleType());
		asset.setName(assetVO.getManufacturerName());
		asset.setBatteryType(assetVO.getBatteryType());
		asset.setBmName(assetVO.getBatteryManufacturerName());
		asset.setBatteryNumber(assetVO.getBatteryNumber());
		asset.setBmYear(assetVO.getManufacturingYear());
		asset.setBmWarranty(assetVO.getBatteryManufacturerWarranty());
		asset.setBmMaintenanceType(assetVO.getBatteryManufacturerMaintenanceType());

		asset.setChasisNo(assetVO.getChasisNo());
		return asset;
	}


	public LoanEntity loanVotoEntity(LoanVO loanVO) {
		LoanEntity loanEntity = new LoanEntity();

		loanEntity.setAssetValue(loanVO.getAssetValue());
		loanEntity.setDownPayment(loanVO.getDownPayment());
		loanEntity.setLoanAmount(loanVO.getLoanAmount());
		loanEntity.setStatus(LoanStatus.NEW);

		// child ojects
		loanEntity.setProfile(this.profileVotoEntity(loanVO.getProfile()));
		loanEntity.setCoProfile(this.profileVotoEntity(loanVO.getCoProfile()));
		loanEntity.setAddress(this.addressVotoEntity(loanVO.getAddress()));
		loanEntity.setCoAddress(this.coAddressVotoEntity(loanVO.getCoAddress()));
		loanEntity.setIncome(this.incomeVotoEntity(loanVO.getIncome()));
		loanEntity.setCoIncome(this.coIncomeVotoEntity(loanVO.getCoIncome()));
		loanEntity.setAsset(this.assetVotoEntity(loanVO.getAsset()));
		return loanEntity;
	}

}
