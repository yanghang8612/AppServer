package com.huachuang.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by Asuka on 2017/4/17.
 */

@Entity
@Table(name = "apply_loan")
public class ApplyLoan {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "house_address")
    private String houseAddress;

    @Column(name = "house_property_card")
    private String housePropertyCard;

    @Column(name = "house_land_sources")
    private String houseLandSources;

    @Column(name = "house_type")
    private String houseType;

    @Column(name = "house_build_year")
    private String houseBuildYear;

    @Column(name = "house_build_area")
    private String houseBuildArea;

    @Column(name = "house_owned_by_others")
    private boolean houseOwnedByOthers;

    @Column(name = "house_is_mortgaged")
    private boolean houseIsMortgaged;

    @Column(name = "house_borrower_is_owner")
    private boolean houseBorrowerIsOwner;

    @Column(name = "house_handing_time")
    private String houseHandingTime;

    @Column(name = "borrower_name")
    private String borrowerName;

    @Column(name = "borrower_phone_number")
    private String borrowerPhoneNumber;

    @Column(name = "borrower_amount")
    private String borrowerAmount;

    @Column(name = "borrower_marriage")
    private String borrowerMarriage;

    @Column(name = "borrower_address")
    private String borrowerAddress;

    @Column(name = "borrower_detailed_address")
    private String borrowerDetailedAddress;

    @Column(name = "apply_time")
    private Date applyTime;

    @Column(name = "apply_state")
    private short applyState;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHousePropertyCard() {
        return housePropertyCard;
    }

    public void setHousePropertyCard(String housePropertyCard) {
        this.housePropertyCard = housePropertyCard;
    }

    public String getHouseLandSources() {
        return houseLandSources;
    }

    public void setHouseLandSources(String houseLandSources) {
        this.houseLandSources = houseLandSources;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseBuildYear() {
        return houseBuildYear;
    }

    public void setHouseBuildYear(String houseBuildYear) {
        this.houseBuildYear = houseBuildYear;
    }

    public String getHouseBuildArea() {
        return houseBuildArea;
    }

    public void setHouseBuildArea(String houseBuildArea) {
        this.houseBuildArea = houseBuildArea;
    }

    public boolean isHouseOwnedByOthers() {
        return houseOwnedByOthers;
    }

    public void setHouseOwnedByOthers(boolean houseOwnedByOthers) {
        this.houseOwnedByOthers = houseOwnedByOthers;
    }

    public boolean isHouseIsMortgaged() {
        return houseIsMortgaged;
    }

    public void setHouseIsMortgaged(boolean houseIsMortgaged) {
        this.houseIsMortgaged = houseIsMortgaged;
    }

    public boolean isHouseBorrowerIsOwner() {
        return houseBorrowerIsOwner;
    }

    public void setHouseBorrowerIsOwner(boolean houseBorrowerIsOwner) {
        this.houseBorrowerIsOwner = houseBorrowerIsOwner;
    }

    public String getHouseHandingTime() {
        return houseHandingTime;
    }

    public void setHouseHandingTime(String houseHandingTime) {
        this.houseHandingTime = houseHandingTime;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerPhoneNumber() {
        return borrowerPhoneNumber;
    }

    public void setBorrowerPhoneNumber(String borrowerPhoneNumber) {
        this.borrowerPhoneNumber = borrowerPhoneNumber;
    }

    public String getBorrowerAmount() {
        return borrowerAmount;
    }

    public void setBorrowerAmount(String borrowerAmount) {
        this.borrowerAmount = borrowerAmount;
    }

    public String getBorrowerMarriage() {
        return borrowerMarriage;
    }

    public void setBorrowerMarriage(String borrowerMarriage) {
        this.borrowerMarriage = borrowerMarriage;
    }

    public String getBorrowerAddress() {
        return borrowerAddress;
    }

    public void setBorrowerAddress(String borrowerAddress) {
        this.borrowerAddress = borrowerAddress;
    }

    public String getBorrowerDetailedAddress() {
        return borrowerDetailedAddress;
    }

    public void setBorrowerDetailedAddress(String borrowerDetailedAddress) {
        this.borrowerDetailedAddress = borrowerDetailedAddress;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public short getApplyState() {
        return applyState;
    }

    public void setApplyState(short applyState) {
        this.applyState = applyState;
    }
}