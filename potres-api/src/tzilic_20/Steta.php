<?php
namespace tzilic_20;

/**
 * @Entity @Table(name="prijava_stete")
 **/
class Steta
{
    /** @Id @Column(type="integer") @GeneratedValue  **/
    private $sifra;

    /** @Column(type="string") **/
    private $vlasnik;

    /** @Column(type="float") **/
	private $geografskaSirina;

	 /** @Column(type="float") **/
	 private $geografskaDuzina;

	  /** @Column(type="float") **/
	  private $iznosStete;
	
	public function __construct($podaci=null)
	{
		if($podaci==null){
			return;
		}
		$this->vlasnik=$podaci->vlasnik;
		$this->geografskaSirina=$podaci->geografskaSirina;
		$this->geografskaDuzina=$podaci->geografskaDuzina;
		$this->iznosStete=$podaci->iznosStete;
	}

	public function getSifra(){
		return $this->sifra;
	}

	public function setSifra($sifra){
		$this->sifra = $sifra;
	}

	public function getVlasnik(){
		return $this->vlasnik;
	}

	public function setVlasnik($vlasnik){
		$this->vlasnik = $vlasnik;
	}

	public function getGeografskaSirina(){
		return $this->geografskaSirina;
	}

	public function setGeografskaSirina($geografskaSirina){
		$this->geografskaSirina = $geografskaSirina;
	}

	public function getGeografskaDuzina(){
		return $this->geografskaDuzina;
	}

	public function setGeografskaDuzina($geografskaDuzina){
		$this->geografskaDuzina = $geografskaDuzina;
	}

	public function getIznosStete(){
		return $this->iznosStete;
	}

	public function setIznosStete($iznosStete){
		$this->iznosStete = $iznosStete;
	}

}