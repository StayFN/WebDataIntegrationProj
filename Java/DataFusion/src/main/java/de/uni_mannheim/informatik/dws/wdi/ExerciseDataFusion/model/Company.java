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
	
	protected String id;
	//protected String provenance;
	private String companyName;
	private String ISIN;
	private String LEI;
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


	@Override
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

	public String getISIN() {
		return ISIN;
	}

	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}

	public String getLEI() {
		return LEI;
	}

	public void setLEI(String lEI) {
		LEI = lEI;
	}

	public Integer getForbes2022Rating() {
		return forbes2022Rating;
	}

	public void setForbes2022Rating(int forbes2022Rating) {
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

	public void setFoundedYear(int foundedYear) {
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

	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}

	public Long getAssets() {
		return assets;
	}

	public void setAssets(long assets) {
		this.assets = assets;
	}

	public Long getProfit() {
		return profit;
	}

	public void setProfit(long profit) {
		this.profit = profit;
	}

	public Long getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(long marketValue) {
		this.marketValue = marketValue;
	}

	public Integer getSizeEmployees() {
		return sizeEmployees;
	}

	public void setSizeEmployees(int sizeEmployees) {
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

	public int getSustGoalYear_NearTerm() {
		return SustGoalYear_NearTerm;
	}

	public void setSustGoalYear_NearTerm(int sustGoalYear_NearTerm) {
		SustGoalYear_NearTerm = sustGoalYear_NearTerm;
	}

	public int getSustGoalYear_LongTerm() {
		return SustGoalYear_LongTerm;
	}

	public void setSustGoalYear_LongTerm(int sustGoalYear_LongTerm) {
		SustGoalYear_LongTerm = sustGoalYear_LongTerm;
	}

	public boolean isNetZeroCommitted() {
		return NetZeroCommitted;
	}

	public void setNetZeroCommitted(boolean netZeroCommitted) {
		NetZeroCommitted = netZeroCommitted;
	}

	public int getNetZeroCommittedYear() {
		return NetZeroCommittedYear;
	}

	public void setNetZeroCommittedYear(int netZeroCommittedYear) {
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
		else if(attribute==ASSETS)
			return getRevenue() != null;
		else
			return false;
	}

	@Override
	public String toString() {
		return String.format("[Company %s: %s / %s / %s / %s / %s / %s / %s]", getIdentifier(), getCompanyName(),
				getCountry(), getIndustries(), getFoundedYear().toString(), getKeyPersons(), getRevenue().toString(), getAssets().toString());
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
