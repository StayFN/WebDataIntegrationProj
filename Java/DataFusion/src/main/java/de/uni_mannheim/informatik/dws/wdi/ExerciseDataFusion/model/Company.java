package de.uni_mannheim.informatik.dws.wdi.ExerciseDataFusion.model;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import de.uni_mannheim.informatik.dws.winter.model.AbstractRecord;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;

public class Company extends AbstractRecord<Attribute> implements Serializable {
	/*
	  example entry <Company> <ID>Forbes_1</ID> <CompanyName>BioNTech</CompanyName>
	  <ISIN>US0378331005</ISIN> <LEI>549300VJTTGNEANFBN84</LEI>
	  <Forbes2022Rating>1</Forbes2022Rating>
			<Industries>
				<Industry>Biotechnology</Industry>
				<Industry>Medicine</Industry>
			</Industries>
		<FoundedYear>2008</FoundedYear>	<Country>Germany</Country>	<Region>Europe</Region>
		<KeyPersons>
			<Person> <Name>Ugur Sahin</Name> <Founder>True</Founder> <Ceo>True</Ceo> <other/>
			</Person>
			</KeyPersons>
			<Revenue>17997279300</Revenue>
			<Assets>14569226100</Assets>
			<Profit>349375755.3</Profit>
			<MarketValue>100000000000</MarketValue>
			<SizeEmployees>5000</SizeEmployees>
			<SizeCategory>small company</SizeCategory>
			<LegalType>AG</LegalType>
			<SustGoalDescription>Target was approved using...</SustGoalDescription>
			<SustGoalStatus_NearTerm>Targets Set</SustGoalStatus_NearTerm>
			<SustGoalStatus_LongTerm>Targets Set</SustGoalStatus_LongTerm>
			<SustGoalClassification_NearTerm>Well-below 2°C</SustGoalClassification_NearTerm>
			<SustGoalClassification_LongTerm>Well-below 2°C</SustGoalClassification_LongTerm>
			<SustGoalYear_NearTerm>2030</SustGoalYear_NearTerm>
			<SustGoalYear_LongTerm>2050</SustGoalYear_LongTerm>
			<NetZeroCommitted>True</NetZeroCommitted>
			<NetZeroCommittedYear>2050</NetZeroCommittedYear>
			</Company>
	 */
	
	private static final long serialVersionUID = 1L;

	public Company(String identifier, String provenance) {
		super(identifier, provenance);
		Industries = new LinkedList<>();
	}
	
	/*public Company(String identifier, String provenance) {
		super(identifier, provenance);
		keyPersons = new LinkedList<>();
	}*/
	
	//protected String id;
	//protected String provenance;
	private String companyName;
	private String isin;
	private String lei;
	private Integer forbes2022Rating;
	private List<String> Industries;
	private Integer foundedYear;
	private String country;
	private String region;
	private List<Person> keyPersons;
	private Long revenue;
	private Long assets;
	private Long profit;
	private Long marketValue;
	private Integer sizeEmployees;
	private String sizeCategory;
	private String legalType;
	private String SustGoalDescription;
	private String SustGoalStatus_NearTerm;
	private String SustGoalStatus_LongTerm;
	private String SustGoalClassification_NearTerm;
	private String SustGoalClassification_LongTerm;
	private Integer SustGoalYear_NearTerm;
	private Integer SustGoalYear_LongTerm;
	private Boolean NetZeroCommitted;
	private Integer NetZeroCommittedYear;


/*	@Override
	public String getIdentifier() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*@Override
	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}
*/
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String iSIN) {
		isin = iSIN;
	}

	public String getLei() {
		return lei;
	}

	public void setLei(String lEI) {
		lei = lEI;
	}

	public Integer getForbes2022Rating() {
		return forbes2022Rating;
	}

	public void setForbes2022Rating(Integer forbes2022Rating) {
		this.forbes2022Rating = forbes2022Rating;
	}

	public List<String> getIndustries() {
		return Industries;
	}

	public void setIndustries(List<String> industries) {
		Industries = industries;
	}

	public Integer getFoundedYear() {
		return foundedYear;
	}

	public void setFoundedYear(Integer foundedYear) {
		this.foundedYear = foundedYear;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<Person> getKeyPersons() {
		return keyPersons;
	}

	public void setKeyPersons(List<Person> keyPersons) {
		this.keyPersons = keyPersons;
	}

	public Long getRevenue() {
		return revenue;
	}

	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}

	public Long getAssets() {
		return assets;
	}

	public void setAssets(Long assets) {
		this.assets = assets;
	}

	public Long getProfit() {
		return profit;
	}

	public void setProfit(Long profit) {
		this.profit = profit;
	}

	public Long getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Long marketValue) {
		this.marketValue = marketValue;
	}

	public Integer getSizeEmployees() {
		return sizeEmployees;
	}

	public void setSizeEmployees(Integer sizeEmployees) {
		this.sizeEmployees = sizeEmployees;
	}

	public String getSizeCategory() {
		return sizeCategory;
	}

	public void setSizeCategory(String sizeCategory) {
		this.sizeCategory = sizeCategory;
	}

	public String getLegalType() {
		return legalType;
	}

	public void setLegalType(String legalType) {
		this.legalType = legalType;
	}

	public String getSustGoalDescription() {
		return SustGoalDescription;
	}

	public void setSustGoalDescription(String sustGoalDescription) {
		SustGoalDescription = sustGoalDescription;
	}

	public String getSustGoalStatus_NearTerm() {
		return SustGoalStatus_NearTerm;
	}

	public void setSustGoalStatus_NearTerm(String sustGoalStatus_NearTerm) {
		SustGoalStatus_NearTerm = sustGoalStatus_NearTerm;
	}

	public String getSustGoalStatus_LongTerm() {
		return SustGoalStatus_LongTerm;
	}

	public void setSustGoalStatus_LongTerm(String sustGoalStatus_LongTerm) {
		SustGoalStatus_LongTerm = sustGoalStatus_LongTerm;
	}

	public String getSustGoalClassification_NearTerm() {
		return SustGoalClassification_NearTerm;
	}

	public void setSustGoalClassification_NearTerm(String sustGoalClassification_NearTerm) {
		SustGoalClassification_NearTerm = sustGoalClassification_NearTerm;
	}

	public String getSustGoalClassification_LongTerm() {
		return SustGoalClassification_LongTerm;
	}

	public void setSustGoalClassification_LongTerm(String sustGoalClassification_LongTerm) {
		SustGoalClassification_LongTerm = sustGoalClassification_LongTerm;
	}

	public Integer getSustGoalYear_NearTerm() {
		return SustGoalYear_NearTerm;
	}

	public void setSustGoalYear_NearTerm(Integer sustGoalYear_NearTerm) {
		SustGoalYear_NearTerm = sustGoalYear_NearTerm;
	}

	public Integer getSustGoalYear_LongTerm() {
		return SustGoalYear_LongTerm;
	}

	public void setSustGoalYear_LongTerm(Integer sustGoalYear_LongTerm) {
		SustGoalYear_LongTerm = sustGoalYear_LongTerm;
	}

	public Boolean isNetZeroCommitted() {
		return NetZeroCommitted;
	}

	public void setNetZeroCommitted(Boolean netZeroCommitted) {
		NetZeroCommitted = netZeroCommitted;
	}

	public Integer getNetZeroCommittedYear() {
		return NetZeroCommittedYear;
	}

	public void setNetZeroCommittedYear(Integer netZeroCommittedYear) {
		NetZeroCommittedYear = netZeroCommittedYear;
	}
	
	private Map<Attribute, Collection<String>> provenance = new HashMap<>();
	private Collection<String> recordProvenance;

	public void setRecordProvenance(Collection<String> provenance) {
		recordProvenance = provenance;
	}

	public Collection<String> getRecordProvenance() {
		return recordProvenance;
	}

	public void setAttributeProvenance(Attribute attribute,
			Collection<String> provenance) {
		this.provenance.put(attribute, provenance);
	}

	public Collection<String> getAttributeProvenance(String attribute) {
		return provenance.get(attribute);
	}

	public String getMergedAttributeProvenance(Attribute attribute) {
		Collection<String> prov = provenance.get(attribute);

		if (prov != null) {
			return StringUtils.join(prov, "+");
		} else {
			return "";
		}
	}
	
	
	public static final Attribute COMPANYNAME = new Attribute("Company name");
	public static final Attribute COUNTRY = new Attribute("Country");
	public static final Attribute INDUSTRY = new Attribute("Industry");
	public static final Attribute YEARFOUNDED = new Attribute("Year Founded");
	public static final Attribute KEYPERSONS = new Attribute("Key Persons");
	public static final Attribute REVENUE = new Attribute("Revenue");
	public static final Attribute ASSETS = new Attribute("Assets");
	public static final Attribute PROFIT = new Attribute("Profit");
	public static final Attribute NETZEROCOMMITEDYEAR = new Attribute("Net Zero Committed Year");

	public static final Attribute ISIN = new Attribute("ISIN");
	public static final Attribute LEI = new Attribute("LEI");
	public static final Attribute FORBES2022RATING = new Attribute("Forbes 2022 Rating");
	public static final Attribute REGION = new Attribute("Region");
	public static final Attribute SIZEEMPLOYEES = new Attribute("Size Employees");
	public static final Attribute SIZECATEGORY = new Attribute("Size Category");
	public static final Attribute LEGALTYPE = new Attribute("Legal Type");
	public static final Attribute SUSTGOALDESCRIPTION = new Attribute("Sustainability Goal Description");
	public static final Attribute SUSTGOALSTATUS_NEAR_TERM = new Attribute("Sustainability Goal Status Near Term");
	public static final Attribute SUSTGOALSTATUS_LONG_TERM = new Attribute("Sustainability Goal Status Long Term");
	public static final Attribute SUSTGOALCLASSIFICATION_NEAR_TERM = new Attribute("Sustainability Goal Classification Near Term");
	public static final Attribute SUSTGOALCLASSIFICATION_LONG_TERM = new Attribute("Sustainability Goal Classification Long Term");
	public static final Attribute SUSTGOALYEAR_NEAR_TERM = new Attribute("Sustainability Goal Year Near Term");
	public static final Attribute SUSTGOALYEAR_LONG_TERM = new Attribute("Sustainability Goal Year Long Term");
	public static final Attribute NETZEROCOMMITTED = new Attribute("Net Zero Committed");


	@Override
	public boolean hasValue(Attribute attribute) {
		if(attribute==COMPANYNAME)
			return getCompanyName() != null && !getCompanyName().isEmpty();
		else if(attribute==COUNTRY)
			return getCountry() != null && !getCountry().isEmpty();
		else if(attribute==INDUSTRY)
			return getIndustries() != null;
		else if(attribute==YEARFOUNDED)
			return getFoundedYear() != null;
		else if(attribute==KEYPERSONS)
			return getKeyPersons() != null && getKeyPersons().size() > 0;
		else if(attribute==REVENUE)
			return getRevenue() != null;
		else if(attribute==PROFIT)
			return getProfit() != null;
		else if(attribute==ASSETS)
			return getAssets() != null;
		else if(attribute==NETZEROCOMMITEDYEAR)
			return getNetZeroCommittedYear() != null;
		else if(attribute == ISIN)
			return getIsin() != null && !getIsin().isEmpty();
		else if(attribute == LEI)
			return getLei() != null && !getLei().isEmpty();
		else if(attribute == FORBES2022RATING)
			return getForbes2022Rating() != null;
		else if(attribute == REGION)
			return getRegion() != null && !getRegion().isEmpty();
		else if(attribute == SIZEEMPLOYEES)
			return getSizeEmployees() != null;
		else if(attribute == SIZECATEGORY)
			return getSizeCategory() != null && !getSizeCategory().isEmpty();
		else if(attribute == LEGALTYPE)
			return getLegalType() != null && !getLegalType().isEmpty();
		else if(attribute == SUSTGOALDESCRIPTION)
			return getSustGoalDescription() != null && !getSustGoalDescription().isEmpty();
		else if(attribute == SUSTGOALSTATUS_NEAR_TERM)
			return getSustGoalStatus_NearTerm() != null && !getSustGoalStatus_NearTerm().isEmpty();
		else if(attribute == SUSTGOALSTATUS_LONG_TERM)
			return getSustGoalStatus_LongTerm() != null && !getSustGoalStatus_LongTerm().isEmpty();
		else if(attribute == SUSTGOALCLASSIFICATION_NEAR_TERM)
			return getSustGoalClassification_NearTerm() != null && !getSustGoalClassification_NearTerm().isEmpty();
		else if(attribute == SUSTGOALCLASSIFICATION_LONG_TERM)
			return getSustGoalClassification_LongTerm() != null && !getSustGoalClassification_LongTerm().isEmpty();
		else if(attribute == SUSTGOALYEAR_NEAR_TERM)
			return getSustGoalYear_NearTerm() != null;
		else if(attribute == SUSTGOALYEAR_LONG_TERM)
			return getSustGoalYear_LongTerm() != null;
		else if(attribute == NETZEROCOMMITTED)
			return isNetZeroCommitted() != null;
		else
			return false;
	}

	@Override
	public String toString() {
		return String.format("[Company %s: %s / %s / %s / %s / %s / %s / %s / %s]",
				getIdentifier() != null ? getIdentifier() : "null",
				getCompanyName() != null ? getCompanyName() : "null",
				getCountry() != null ? getCountry() : "null",
				getIndustries() != null ? getIndustries().toString() : "null",
				getFoundedYear() != null ? getFoundedYear().toString() : "null",
				getKeyPersons() != null ? getKeyPersons().toString() : "null",
				getRevenue() != null ? getRevenue().toString() : "null",
				getAssets() != null ? getAssets().toString() : "null",
				getProfit() != null ? getProfit().toString() : "null"
		);
	}




	@Override
	public int hashCode() {
		return getIdentifier().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Company){
			return this.getIdentifier().equals(((Company) obj).getIdentifier());
		}else
			return false;
	}

	
	
}
