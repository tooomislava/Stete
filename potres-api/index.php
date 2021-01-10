<?php

require 'vendor/autoload.php';
use tzilic_20\Steta;
use tzilic_20\Pomocno;

Flight::route('GET /v1/prijave', function(){
   $doctrineBootstrap = Flight::db();
   $em = $doctrineBootstrap->getEntityManager();
   $repozitorij=$em->getRepository('tzilic_20\Steta');
   $stete = $repozitorij->findAll();
   $podaci = $doctrineBootstrap->getJson($stete);
   Flight::json(Pomocno::odgovor(false,'OK',json_decode($podaci)));
});



Flight::route('POST /v1/prijava', function(){
  if(strlen(Flight::request()->data->vlasnik)===0){
    Flight::json(Pomocno::odgovor(true,'Vlasnik obavezno',null));
    return;
  }
  if(strlen(Flight::request()->data->geografskaSirina)===0){
    Flight::json(Pomocno::odgovor(true,'Geografska širina obavezno',null));
    return;
  }
  if(strlen(Flight::request()->data->geografskaDuzina)===0){
    Flight::json(Pomocno::odgovor(true,'Geografska dužina obavezno',null));
    return;
  }
  if(strlen(Flight::request()->data->iznosStete)===0){
    Flight::json(Pomocno::odgovor(true,'Iznos štete obavezno',null));
    return;
  }
  $steta = new Steta(Flight::request()->data);
  $doctrineBootstrap = Flight::db();
  $em = $doctrineBootstrap->getEntityManager();
  $em->persist($steta);
  $em->flush();
  $podaci = $doctrineBootstrap->getJson($steta);
  Flight::json(Pomocno::odgovor(false,'OK',[json_decode($podaci)]));
  header("HTTP/1.1 201 Created");
});



Flight::route('PUT /v1/prijava/@sifra', function($sifra){
    $doctrineBootstrap = Flight::db();
    $em = $doctrineBootstrap->getEntityManager();
    $repozitorij=$em->getRepository('tzilic_20\Steta');
    $steta = $repozitorij->find($sifra);
    $steta->setVlasnik(Flight::request()->data->vlasnik);
    $steta->setGeografskaSirina(Flight::request()->data->geografskaSirina);
    $steta->setGeografskaDuzina(Flight::request()->data->geografskaDuzina);
    $steta->setIznosStete(Flight::request()->data->iznosStete);

    $em->persist($steta);
    $em->flush();
    Flight::json(Pomocno::odgovor(false,'OK',null));
  });

  Flight::route('DELETE /v1/prijava/@sifra', function($sifra){
    $doctrineBootstrap = Flight::db();
    $em = $doctrineBootstrap->getEntityManager();
    $repozitorij=$em->getRepository('tzilic_20\Steta');
    $steta = $repozitorij->find($sifra);
    $em->remove($steta);
    $em->flush();
    Flight::json(Pomocno::odgovor(false,'OK',[$steta]));
  });

Flight::route('/', function(){
  Flight::json(Pomocno::odgovor(true,'nepotpuni podaci'));
});

Flight::map('notFound', function(){
  Flight::json(Pomocno::odgovor(true,'nepotpuni podaci'));
});

require_once 'bootstrap.php';

Flight::register('db','DoctrineBootstrap');

Flight::start();