/* ===============================================
   MODERN LAB EVALUATION SHEET - JAVASCRIPT
   =============================================== */

// Application state
const AppState = {
  cycle1End: 5,
  cycle2End: 10,
  manualMode: false,
  experiments: {
    cycle1: [],
    cycle2: []
  },
  evaluations: {
    cycle1: { TK: 0, TD: 0, LI: 0, B: 0, C: 0 },
    cycle2: { TK: 0, TD: 0, LI: 0, B: 0, C: 0 }
  },
  criteriaWeights: {
    TK: 30, TD: 30, LI: 10, B: 10, C: 20
  }
};

// DOM elements cache
const Elements = {
  cycle1End: null,
  cycle2End: null,
  cycle2Start: null,
  manualMode: null,
  cycle1Experiments: null,
  cycle2Experiments: null,
  cycle1Avg: null,
  cycle2Avg: null,
  cycle1Range: null,
  cycle2RangeStart: null,
  cycle2RangeEnd: null,
  finalAverage: null,
  darkModeToggle: null
};

// Initialize the application when DOM is loaded
document.addEventListener('DOMContentLoaded', initializeApp);

function initializeApp() {
  cacheElements();
  setupEventListeners();
  updateCycles();
  generateExperimentInputs();
  loadTheme();
}

function cacheElements() {
  Elements.cycle1End = document.getElementById('cycle1-end');
  Elements.cycle2End = document.getElementById('cycle2-end');
  Elements.cycle2Start = document.getElementById('cycle2-start');
  Elements.manualMode = document.getElementById('manual-mode');
  Elements.cycle1Experiments = document.getElementById('cycle1-experiments');
  Elements.cycle2Experiments = document.getElementById('cycle2-experiments');
  Elements.cycle1Avg = document.getElementById('cycle1-avg');
  Elements.cycle2Avg = document.getElementById('cycle2-avg');
  Elements.cycle1Range = document.getElementById('cycle1-range');
  Elements.cycle2RangeStart = document.getElementById('cycle2-range-start');
  Elements.cycle2RangeEnd = document.getElementById('cycle2-range-end');
  Elements.finalAverage = document.getElementById('final-average');
  Elements.darkModeToggle = document.querySelector('.dark-mode-toggle');
  
  // Debug: Check if elements are found
  console.log('Elements cached:', {
    cycle1End: !!Elements.cycle1End,
    cycle2End: !!Elements.cycle2End,
    cycle2Start: !!Elements.cycle2Start,
    darkModeToggle: !!Elements.darkModeToggle
  });
}

function setupEventListeners() {
  // Cycle configuration - add both input and change listeners
  if (Elements.cycle1End) {
    Elements.cycle1End.addEventListener('input', handleCycle1Change);
    Elements.cycle1End.addEventListener('change', handleCycle1Change);
    Elements.cycle1End.addEventListener('keyup', handleCycle1Change);
    Elements.cycle1End.addEventListener('blur', validateCycle1Input);
  }
  
  if (Elements.cycle2End) {
    Elements.cycle2End.addEventListener('input', handleCycle2Change);
    Elements.cycle2End.addEventListener('change', handleCycle2Change);
    Elements.cycle2End.addEventListener('keyup', handleCycle2Change);
    Elements.cycle2End.addEventListener('blur', validateCycle2Input);
    console.log('Cycle2End event listeners attached'); // Debug
  }
  
  // Manual mode toggle
  if (Elements.manualMode) {
    Elements.manualMode.addEventListener('change', handleManualModeToggle);
  }
  
  // Evaluation grid clicks
  document.addEventListener('click', handleEvaluationClick);
  
  // Dark mode toggle
  if (Elements.darkModeToggle) {
    Elements.darkModeToggle.addEventListener('click', toggleDarkMode);
    console.log('Dark mode toggle event listener attached'); // Debug
  } else {
    console.error('Dark mode toggle button not found!'); // Debug
  }
  
  // Accordion functionality
  document.addEventListener('click', handleAccordionClick);
}

/* ===============================================
   CYCLE CONFIGURATION
   =============================================== */

function handleCycle1Change() {
  const value = parseInt(Elements.cycle1End.value);
  // Only update if value is valid and different from current state
  if (!isNaN(value) && value >= 1 && value <= 15 && value !== AppState.cycle1End) {
    AppState.cycle1End = value;
    updateCycles();
    generateExperimentInputs();
    calculateAverages();
  }
  // Don't reset the input value - let user continue typing
}

function handleCycle2Change() {
  const value = parseInt(Elements.cycle2End.value);
  const minValue = AppState.cycle1End + 1;
  
  console.log('Cycle 2 change:', value, 'Min:', minValue); // Debug log
  
  // Only update if value is valid and different from current state
  if (!isNaN(value) && value >= minValue && value <= 30 && value !== AppState.cycle2End) {
    AppState.cycle2End = value;
    updateCycles();
    generateExperimentInputs();
    calculateAverages();
  }
  // Don't reset the input value - let user continue typing
}

function updateCycles() {
  const cycle2Start = AppState.cycle1End + 1;
  
  // Update cycle 2 minimum value
  Elements.cycle2End.min = cycle2Start;
  
  // Update display elements
  Elements.cycle2Start.textContent = cycle2Start;
  Elements.cycle1Range.textContent = AppState.cycle1End;
  Elements.cycle2RangeStart.textContent = cycle2Start;
  Elements.cycle2RangeEnd.textContent = AppState.cycle2End;
  
  // Reset experiments arrays
  AppState.experiments.cycle1 = new Array(AppState.cycle1End).fill(0);
  AppState.experiments.cycle2 = new Array(AppState.cycle2End - AppState.cycle1End).fill(0);
}

/* ===============================================
   EXPERIMENT INPUT GENERATION
   =============================================== */

function generateExperimentInputs() {
  generateCycleExperiments(1, AppState.cycle1End, Elements.cycle1Experiments);
  generateCycleExperiments(AppState.cycle1End + 1, AppState.cycle2End, Elements.cycle2Experiments);
}

function generateCycleExperiments(start, end, container) {
  container.innerHTML = '';
  
  // Determine which cycle this is based on the start value
  const cycleNumber = start <= AppState.cycle1End ? 1 : 2;
  
  for (let i = start; i <= end; i++) {
    const experimentItem = document.createElement('div');
    experimentItem.className = 'experiment-item';
    
    experimentItem.innerHTML = `
      <label for="exp-${i}">Exp ${i}</label>
      <input 
        type="number" 
        id="exp-${i}" 
        class="experiment-input" 
        min="0" 
        max="100" 
        step="1"
        data-experiment="${i}"
        data-cycle="${cycleNumber}"
        placeholder="0-100"
      >
    `;
    
    container.appendChild(experimentItem);
    
    // Add event listener
    const input = experimentItem.querySelector('.experiment-input');
    input.addEventListener('input', handleExperimentInput);
    input.addEventListener('blur', validateExperimentInput);
  }
}

function handleExperimentInput(event) {
  const input = event.target;
  const experimentNum = parseInt(input.dataset.experiment);
  const cycle = parseInt(input.dataset.cycle);
  const value = parseInt(input.value) || 0;
  
  // Update state
  if (cycle === 1) {
    AppState.experiments.cycle1[experimentNum - 1] = value;
  } else {
    AppState.experiments.cycle2[experimentNum - AppState.cycle1End - 1] = value;
  }
  
  // Calculate averages
  calculateAverages();
}

function validateExperimentInput(event) {
  const input = event.target;
  let value = parseInt(input.value);
  
  if (isNaN(value) || value < 0) {
    value = 0;
  } else if (value > 100) {
    value = 100;
  }
  
  input.value = value;
  handleExperimentInput(event);
}

/* ===============================================
   AVERAGE CALCULATIONS
   =============================================== */

function calculateAverages() {
  const cycle1Avg = calculateCycleAverage(AppState.experiments.cycle1);
  const cycle2Avg = calculateCycleAverage(AppState.experiments.cycle2);
  
  Elements.cycle1Avg.value = cycle1Avg > 0 ? cycle1Avg : '';
  Elements.cycle2Avg.value = cycle2Avg > 0 ? cycle2Avg : '';
  
  // Auto-calculate evaluations if not in manual mode
  if (!AppState.manualMode) {
    if (cycle1Avg > 0) {
      autoCalculateEvaluation(1, cycle1Avg);
    }
    if (cycle2Avg > 0) {
      autoCalculateEvaluation(2, cycle2Avg);
    }
  }
  
  updateResultsTable();
}

function calculateCycleAverage(experiments) {
  const validExperiments = experiments.filter(exp => exp > 0);
  if (validExperiments.length === 0) return 0;
  
  const sum = validExperiments.reduce((acc, exp) => acc + exp, 0);
  let average = sum / validExperiments.length;
  
  // Ensure minimum of 20 and even number
  if (average < 20) average = 20;
  if (average % 2 !== 0) {
    average = Math.round(average / 2) * 2;
  }
  
  return Math.min(100, average);
}

/* ===============================================
   EVALUATION SYSTEM
   =============================================== */

function handleManualModeToggle() {
  AppState.manualMode = Elements.manualMode.checked;
  
  if (!AppState.manualMode) {
    // Auto-calculate based on current averages
    const cycle1Avg = parseInt(Elements.cycle1Avg.value) || 0;
    const cycle2Avg = parseInt(Elements.cycle2Avg.value) || 0;
    
    if (cycle1Avg > 0) autoCalculateEvaluation(1, cycle1Avg);
    if (cycle2Avg > 0) autoCalculateEvaluation(2, cycle2Avg);
  } else {
    // Clear pre-calculated results when switching to manual mode
    AppState.calculatedResults = {};
  }
  
  updateResultsTable();
}

function handleEvaluationClick(event) {
  if (!event.target.classList.contains('grade-cell')) return;
  if (!event.target.dataset.grade) return;
  
  const cycle = parseInt(event.target.dataset.cycle);
  const criterion = event.target.dataset.criterion;
  const grade = parseInt(event.target.dataset.grade);
  
  // Clear previous selections in this row
  const row = event.target.parentElement;
  row.querySelectorAll('.grade-cell').forEach(cell => {
    cell.classList.remove('selected');
  });
  
  // Select current cell
  event.target.classList.add('selected');
  
  // Update state
  AppState.evaluations[`cycle${cycle}`][criterion] = grade;
  
  // Clear pre-calculated results for this cycle since user is manually selecting
  if (AppState.calculatedResults) {
    delete AppState.calculatedResults[`cycle${cycle}`];
  }
  
  updateResultsTable();
}

function autoCalculateEvaluation(cycle, average) {
  const results = calculateGradesFromAverage(average);
  
  // Extract just the grade levels for visual display
  const evaluation = {
    TK: Math.round(results.TK / 6), // Convert back to grade level (1-5)
    TD: Math.round(results.TD / 6),
    LI: Math.round(results.LI / 2),
    B: Math.round(results.B / 2),
    C: Math.round(results.C / 4)
  };
  
  // Store the actual calculated results for final calculations
  AppState.evaluations[`cycle${cycle}`] = evaluation;
  AppState.calculatedResults = AppState.calculatedResults || {};
  AppState.calculatedResults[`cycle${cycle}`] = results;
  
  // Update visual selection
  updateEvaluationGrid(cycle, evaluation);
}

function calculateGradesFromAverage(marks) {
  // Original algorithm from the legacy code
  if (!marks || marks < 20) return { TK: 0, TD: 0, LI: 0, B: 0, C: 0 };
  
  let diff = 0;
  let l = [5, 5, 5, 5, 5]; // [TK, TD, LI, B, C]
  let w = [6, 6, 2, 2, 4]; // weights for calculation
  
  let lvl = Math.floor((marks - marks % 20) / 20) + Math.ceil(marks % 20 / 20);
  l = l.map(() => lvl);
  
  if (marks % 20 !== 0) {
    diff = 20 - marks % 20;
  } else {
    diff = 0;
  }
  
  // Distribute the difference according to original algorithm
  while (diff > 0) {
    if (diff >= 6) {
      l[1]--; // TD
      diff -= 6;
    }
    if (diff >= 4) {
      l[4]--; // C
      diff -= 4;
    }
    if (diff >= 2 && diff <= 4) {
      l[2]--; // LI
      diff -= 2;
    }
    if (diff >= 6) {
      l[0]--; // TK
      diff -= 6;
    }
    if (diff >= 2) {
      l[3]--; // B
      diff -= 2;
    }
  }
  
  // Calculate weighted results
  const results = {
    TK: w[0] * l[0],
    TD: w[1] * l[1], 
    LI: w[2] * l[2],
    B: w[3] * l[3],
    C: w[4] * l[4]
  };
  
  results.total = results.TK + results.TD + results.LI + results.B + results.C;
  
  return results;
}

function updateEvaluationGrid(cycle, evaluation) {
  Object.entries(evaluation).forEach(([criterion, grade]) => {
    // Clear previous selections
    const cells = document.querySelectorAll(`[data-criterion="${criterion}"][data-cycle="${cycle}"]`);
    cells.forEach(cell => cell.classList.remove('selected'));
    
    // Select the appropriate grade
    const selectedCell = document.querySelector(`[data-criterion="${criterion}"][data-cycle="${cycle}"][data-grade="${grade}"]`);
    if (selectedCell) {
      selectedCell.classList.add('selected');
    }
  });
}

/* ===============================================
   RESULTS TABLE
   =============================================== */

function updateResultsTable() {
  const results = calculateResults();
  
  // Update cycle results
  updateCycleResults(1, results.cycle1);
  updateCycleResults(2, results.cycle2);
  
  // Update totals
  updateTotalResults(results.totals);
  updateAverageResults(results.averages);
  
  // Update final average display
  Elements.finalAverage.textContent = results.finalAverage || '--';
  document.getElementById('final-avg').textContent = results.finalAverage || '-';
}

function calculateResults() {
  // Use pre-calculated results if available (from auto-calculation)
  // Otherwise calculate manually based on selected grades
  
  let cycle1Results, cycle2Results;
  
  if (AppState.calculatedResults && AppState.calculatedResults.cycle1) {
    cycle1Results = AppState.calculatedResults.cycle1;
  } else {
    cycle1Results = calculateCycleResults(AppState.evaluations.cycle1, AppState.criteriaWeights);
  }
  
  if (AppState.calculatedResults && AppState.calculatedResults.cycle2) {
    cycle2Results = AppState.calculatedResults.cycle2;
  } else {
    cycle2Results = calculateCycleResults(AppState.evaluations.cycle2, AppState.criteriaWeights);
  }
  
  // Calculate totals
  const totals = {
    TK: cycle1Results.TK + cycle2Results.TK,
    TD: cycle1Results.TD + cycle2Results.TD,
    LI: cycle1Results.LI + cycle2Results.LI,
    B: cycle1Results.B + cycle2Results.B,
    C: cycle1Results.C + cycle2Results.C,
    total: cycle1Results.total + cycle2Results.total
  };
  
  // Calculate averages
  const averages = {
    TK: totals.TK / 2,
    TD: totals.TD / 2,
    LI: totals.LI / 2,
    B: totals.B / 2,
    C: totals.C / 2,
    total: totals.total / 2
  };
  
  return {
    cycle1: cycle1Results,
    cycle2: cycle2Results,
    totals,
    averages,
    finalAverage: averages.total > 0 ? Math.round(averages.total) : null
  };
}

function calculateCycleResults(evaluation, weights) {
  const results = {
    TK: evaluation.TK * 6, // Use the same weights as original (6, 6, 2, 2, 4)
    TD: evaluation.TD * 6,
    LI: evaluation.LI * 2,
    B: evaluation.B * 2,
    C: evaluation.C * 4
  };
  
  results.total = results.TK + results.TD + results.LI + results.B + results.C;
  
  return results;
}

function updateCycleResults(cycle, results) {
  const prefix = `c${cycle}`;
  document.getElementById(`${prefix}-tk`).textContent = results.TK || '-';
  document.getElementById(`${prefix}-td`).textContent = results.TD || '-';
  document.getElementById(`${prefix}-li`).textContent = results.LI || '-';
  document.getElementById(`${prefix}-b`).textContent = results.B || '-';
  document.getElementById(`${prefix}-c`).textContent = results.C || '-';
  document.getElementById(`${prefix}-total`).textContent = results.total || '-';
}

function updateTotalResults(totals) {
  document.getElementById('total-tk').textContent = totals.TK || '-';
  document.getElementById('total-td').textContent = totals.TD || '-';
  document.getElementById('total-li').textContent = totals.LI || '-';
  document.getElementById('total-b').textContent = totals.B || '-';
  document.getElementById('total-c').textContent = totals.C || '-';
  document.getElementById('grand-total').textContent = totals.total || '-';
}

function updateAverageResults(averages) {
  document.getElementById('avg-tk').textContent = averages.TK > 0 ? averages.TK.toFixed(1) : '-';
  document.getElementById('avg-td').textContent = averages.TD > 0 ? averages.TD.toFixed(1) : '-';
  document.getElementById('avg-li').textContent = averages.LI > 0 ? averages.LI.toFixed(1) : '-';
  document.getElementById('avg-b').textContent = averages.B > 0 ? averages.B.toFixed(1) : '-';
  document.getElementById('avg-c').textContent = averages.C > 0 ? averages.C.toFixed(1) : '-';
}

/* ===============================================
   ACCORDION FUNCTIONALITY
   =============================================== */

function handleAccordionClick(event) {
  if (!event.target.closest('.card-header')) return;
  
  const header = event.target.closest('.card-header');
  if (!header.onclick) return; // Only handle headers with onclick
  
  return; // Let the existing onclick handle it
}

function toggleAccordion(contentId) {
  const content = document.getElementById(contentId);
  const header = content.previousElementSibling;
  const icon = header.querySelector('.accordion-icon');
  
  if (content.classList.contains('active')) {
    content.classList.remove('active');
    header.setAttribute('aria-expanded', 'false');
    icon.style.transform = 'rotate(-90deg)';
  } else {
    content.classList.add('active');
    header.setAttribute('aria-expanded', 'true');
    icon.style.transform = 'rotate(0deg)';
  }
}

/* ===============================================
   THEME MANAGEMENT
   =============================================== */

function loadTheme() {
  // Default to light theme (matching the app's theme)
  const savedTheme = localStorage.getItem('theme') || 'light';
  
  console.log('Loading theme:', savedTheme); // Debug
  document.documentElement.setAttribute('data-theme', savedTheme);
  console.log('Theme set to:', document.documentElement.getAttribute('data-theme')); // Debug
}

function toggleDarkMode() {
  console.log('toggleDarkMode called!'); // Debug
  
  const currentTheme = document.documentElement.getAttribute('data-theme') || 'light';
  const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
  
  console.log('Toggling theme from', currentTheme, 'to', newTheme); // Debug
  
  document.documentElement.setAttribute('data-theme', newTheme);
  localStorage.setItem('theme', newTheme);
  
  console.log('Theme applied:', document.documentElement.getAttribute('data-theme')); // Debug
  
  // Force a style recalculation
  document.body.style.display = 'none';
  document.body.offsetHeight; // Trigger reflow
  document.body.style.display = '';
}

/* ===============================================
   RESET FUNCTIONALITY
   =============================================== */

function resetAll() {
  // Reset state
  AppState.experiments.cycle1.fill(0);
  AppState.experiments.cycle2.fill(0);
  AppState.evaluations.cycle1 = { TK: 0, TD: 0, LI: 0, B: 0, C: 0 };
  AppState.evaluations.cycle2 = { TK: 0, TD: 0, LI: 0, B: 0, C: 0 };
  AppState.calculatedResults = {}; // Clear calculated results
  AppState.manualMode = false;
  
  // Reset form inputs
  document.querySelectorAll('.experiment-input').forEach(input => {
    input.value = '';
  });
  
  Elements.cycle1Avg.value = '';
  Elements.cycle2Avg.value = '';
  Elements.manualMode.checked = false;
  
  // Reset evaluation grids
  document.querySelectorAll('.grade-cell.selected').forEach(cell => {
    cell.classList.remove('selected');
  });
  
  // Reset results
  updateResultsTable();
  
  // Scroll to top
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

/* ===============================================
   UTILITY FUNCTIONS
   =============================================== */

function debounce(func, wait) {
  let timeout;
  return function executedFunction(...args) {
    const later = () => {
      clearTimeout(timeout);
      func(...args);
    };
    clearTimeout(timeout);
    timeout = setTimeout(later, wait);
  };
}

// Add smooth scrolling for hash links
document.addEventListener('click', function(event) {
  if (event.target.matches('a[href^="#"]')) {
    event.preventDefault();
    const target = document.querySelector(event.target.getAttribute('href'));
    if (target) {
      target.scrollIntoView({ behavior: 'smooth' });
    }
  }
});

// Handle keyboard navigation for accessibility
document.addEventListener('keydown', function(event) {
  if (event.key === 'Enter' || event.key === ' ') {
    if (event.target.classList.contains('grade-cell') && event.target.dataset.grade) {
      event.preventDefault();
      event.target.click();
    }
  }
});

// Add touch feedback for mobile
document.addEventListener('touchstart', function() {}, { passive: true });

// Export functions for global access (needed for onclick handlers)
window.toggleAccordion = toggleAccordion;
window.toggleDarkMode = toggleDarkMode;
window.resetAll = resetAll;

/* ===============================================
   VALIDATION FUNCTIONS
   =============================================== */

// Validation functions that only trigger on blur
function validateCycle1Input() {
  const value = parseInt(Elements.cycle1End.value);
  if (isNaN(value) || value < 1 || value > 15) {
    Elements.cycle1End.value = AppState.cycle1End;
    showTooltip(Elements.cycle1End, 'Please enter a value between 1 and 15');
  }
}

function validateCycle2Input() {
  const value = parseInt(Elements.cycle2End.value);
  const minValue = AppState.cycle1End + 1;
  if (isNaN(value) || value < minValue || value > 30) {
    Elements.cycle2End.value = AppState.cycle2End;
    showTooltip(Elements.cycle2End, `Please enter a value between ${minValue} and 30`);
  }
}

/* ===============================================
   TOOLTIP UTILITY
   =============================================== */

function showTooltip(element, message) {
  // Remove any existing tooltip
  const existingTooltip = element.parentNode.querySelector('.validation-tooltip');
  if (existingTooltip) {
    existingTooltip.remove();
  }
  
  // Create tooltip
  const tooltip = document.createElement('div');
  tooltip.className = 'validation-tooltip';
  tooltip.textContent = message;
  
  // Position tooltip
  tooltip.style.position = 'absolute';
  tooltip.style.top = '100%';
  tooltip.style.left = '50%';
  tooltip.style.transform = 'translateX(-50%)';
  tooltip.style.background = 'var(--error-color, #ef4444)';
  tooltip.style.color = 'white';
  tooltip.style.padding = '4px 8px';
  tooltip.style.borderRadius = '4px';
  tooltip.style.fontSize = '12px';
  tooltip.style.zIndex = '1000';
  tooltip.style.marginTop = '4px';
  tooltip.style.whiteSpace = 'nowrap';
  
  // Add to parent (which should have position relative)
  element.parentNode.style.position = 'relative';
  element.parentNode.appendChild(tooltip);
  
  // Auto-remove after 3 seconds
  setTimeout(() => {
    if (tooltip.parentNode) {
      tooltip.remove();
    }
  }, 3000);
}