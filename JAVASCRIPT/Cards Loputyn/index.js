document.addEventListener("click", () => {
  if (bgAudio.paused) {
    bgAudio.volume = 0.3; // Volume consigliato: 30%
    bgAudio.play().catch(e => console.warn("Autoplay bloccato", e));
  }
}, { once: true });

// MODALE
const cards = document.querySelectorAll('.card');
const modal = document.getElementById("modal");
const modalImg = document.getElementById("modal-img");
const modalTitle = document.getElementById("modal-title");
const modalDesc = document.getElementById("modal-desc");

// Suoni
const modalOpenSound = new Audio("sounds/openclick2.wav");
const modalCloseSound = new Audio("sounds/closeclick.mp3");

// zoom e drag 
let scale = 1;
const scaleStep = 0.1;
const maxScale = 3;
const minScale = 1;
let posX = 0;
let posY = 0;
let isDragging = false;
let startX, startY;
modalImg.style.transformOrigin = 'center center';

// Funzione per aggiornare trasformazione immagine (zoom + posizione)
function updateTransform() {
  modalImg.style.transform = `translate(${posX}px, ${posY}px) scale(${scale})`;
}

// Apre la modale con suono e reset zoom e posizione
document.querySelectorAll(".card").forEach(card => {
  card.addEventListener("click", () => {
    const imgSrc = card.getAttribute("data-img");
    const title = card.getAttribute("data-title");
    const desc = card.getAttribute("data-desc");

    modalImg.src = imgSrc;
    modalTitle.textContent = title;
    modalDesc.textContent = desc;

    modal.style.display = "block";
    modal.classList.add("show");

    modalOpenSound.currentTime = 0;
    modalOpenSound.volume = 1;
    modalOpenSound.play();

    // Reset zoom e posizione
    scale = 1;
    posX = 0;
    posY = 0;
    updateTransform();
  });
});

// Zoom con scroll mouse
modalImg.addEventListener('wheel', (e) => {
  e.preventDefault();

  const prevScale = scale;

  if (e.deltaY < 0) {
    // Zoom in
    scale = Math.min(scale + scaleStep, maxScale);
  } else {
    // Zoom out
    scale = Math.max(scale - scaleStep, minScale);
  }

  // Quando si fa zoom, centriamo lo zoom verso il cursore
  // Per semplicità ora non spostiamo la posizione, ma si può aggiungere in futuro

  // Se zoom minore o uguale a 1, reset posizione
  if (scale <= 1) {
    posX = 0;
    posY = 0;
  } else if (prevScale === 1 && scale > 1) {
    // quando inizia zoom, reset posizione
    posX = 0;
    posY = 0;
  }

  updateTransform();
});

// Zoom toggle con click sull'immagine
modalImg.addEventListener('click', () => {
  if (scale > 1) {
    scale = 1; // reset zoom
    posX = 0;
    posY = 0;
  } else {
    scale = 2; // zoom 2x
    posX = 0;
    posY = 0;
  }
  updateTransform();
});

// Drag per spostare immagine zoomata
modalImg.addEventListener('mousedown', (e) => {
  if (scale <= 1) return; // drag solo se zoom > 1

  isDragging = true;
  startX = e.clientX - posX;
  startY = e.clientY - posY;

  // Cambia cursore per feedback utente
  modalImg.style.cursor = 'grabbing';

  // Previeni selezione testo o immagini mentre si trascina
  e.preventDefault();
});

window.addEventListener('mouseup', () => {
  if (!isDragging) return;
  isDragging = false;
  modalImg.style.cursor = scale > 1 ? 'grab' : 'zoom-in';
});

window.addEventListener('mousemove', (e) => {
  if (!isDragging) return;

  posX = e.clientX - startX;
  posY = e.clientY - startY;

  // Optional: limitare lo spostamento per non uscire troppo dall'area

  updateTransform();
});

// Chiude la modale con suono e fade out
function closeModal() {
  modal.classList.remove("show");

  modalCloseSound.currentTime = 0;
  modalCloseSound.volume = 1;
  modalCloseSound.play();
  fadeOutAudio(modalCloseSound, 600); // durata fade-out in ms

  // Reset zoom e posizione immagine quando chiudi
  scale = 1;
  posX = 0;
  posY = 0;
  updateTransform();

  setTimeout(() => {
    modal.style.display = "none";
  }, 300);
}

// Funzione per dissolvenza audio
function fadeOutAudio(audio, duration = 500) {
  const stepTime = 50;
  const steps = duration / stepTime;
  let currentStep = 0;
  const initialVolume = audio.volume;

  const fade = setInterval(() => {
    currentStep++;
    audio.volume = Math.max(0, initialVolume * (1 - currentStep / steps));
    if (currentStep >= steps) {
      clearInterval(fade);
      audio.pause();
      audio.volume = initialVolume; // reset per usi futuri
    }
  }, stepTime);
}


function toggleTheme() {
  document.body.classList.toggle("light");
}

const bgAudio = document.getElementById("background-audio");
const audioToggle = document.getElementById("audio-toggle");

// Volume iniziale
bgAudio.volume = 0.3;

// Attiva audio solo al primo clic per evitare blocchi browser
document.addEventListener("click", () => {
  if (bgAudio.paused) {
    bgAudio.play().catch(e => console.warn("Autoplay bloccato", e));
  }
}, { once: true });


audioToggle.addEventListener("click", () => {
  bgAudio.muted = !bgAudio.muted;
  audioToggle.textContent = bgAudio.muted ? "🔇" : "🔈";
});



